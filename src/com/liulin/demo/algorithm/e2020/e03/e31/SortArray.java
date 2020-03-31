package com.liulin.demo.algorithm.e2020.e03.e31;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 * Create by DbL on 2020/3/31 0031
 */
public class SortArray {
    public static void main(String[] args) {
        int[] arr = {3, 5, 2, 6, 9, 8, 1};
        Arrays.sort(arr);
        Long t1 = System.currentTimeMillis();
        selectSort(arr);
        Long t2 = System.currentTimeMillis();
        System.out.println("选择排序用时：" + (t2 - t1));
        insertSort(arr);
        Long t3 = System.currentTimeMillis();
        System.out.println("插入排序用时：" + (t3 - t2));
        bubbleSort(arr);
        Long t4 = System.currentTimeMillis();
        System.out.println("冒泡排序用时：" + (t4 - t3));
        hillSortSort(arr);
        Long t5 = System.currentTimeMillis();
        System.out.println("希尔排序用时：" + (t5 - t4));
        mergeSort(arr);
        Long t6 = System.currentTimeMillis();
        System.out.println("归并排序用时：" + (t6 - t5));
        quickSort(arr);
        Long t7 = System.currentTimeMillis();
        System.out.println("快速排序用时：" + (t7 - t6));
        System.out.println(Arrays.toString(dualPivotQuicksort(arr)));


    }

    /**
     * 非比较排序
     * @param arr
     * @return
     */
    public static int[] dualPivotQuicksort (int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        int max = arr[0],min = arr[0];
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(arr[i], max);
            min = Math.min(arr[i], min);
        }
        int[] counter = new int[max - min + 1];
        for (int i = 0; i < n; i++) {
            counter[arr[i]-min]++;
        }
        int index = 0;
        for (int i = min; i < max; i++) {
            int numCount = counter[i-min];
            while (numCount-->0){
                arr[index++] = i;
            }
        }
        return arr;
    }
    /**
     * 堆排序
     * 将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
     * 将其与末尾元素进行交换，此时末尾就为最大值。
     * 然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
     * 如此反复执行，便能得到一个有序序列了
     *
     * @param arr
     * @return
     */
    public static int[] headSort(int[] arr) {
        //构建大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(arr, i, arr.length);
        }
        //调整堆结构+交换堆顶元素与末尾元素
        for (int j = arr.length - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swap(arr, 0, j);
            //重新对堆进行调整
            adjustHeap(arr, 0, j);
        }

        return arr;
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }

    /**
     * 快速排序
     * 随机选取一个元素作为基准元素,为了方便，这里取第一个
     * 将数组中小于基准元素的元素放在基准原始的左边，将大于基准元素的元素放到基准元素的右边
     * 然后递归对两遍的元素分别进行此操作，直到数组的元素为1
     *
     * @param arr
     * @return
     */
    public static int[] quickSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;
        return quickSort(arr, 0, 1);
    }

    public static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            //获取中轴元素所处的位置
            int mid = partition(arr, left, right);
            //进行分割
            arr = quickSort(arr, left, mid - 1);
            arr = quickSort(arr, mid + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        //选取基准元素
        int base = arr[left];
        int i = left + 1;
        int j = right;
        while (true) {
            // 向右找到第一个小于等于 base 的元素位置
            while (i <= j && arr[i] <= base) i++;
            // 向左找到第一个大于等于 pivot 的元素位置
            while (i <= j && arr[j] >= base) j--;
            if (i >= j)
                break;
            //交换两个元素的位置，使得左边的元素不大于pivot,右边的不小于pivot
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        arr[left] = arr[j];
        // 使基准元素处于有序的位置
        arr[j] = base;
        return j;
    }

    /**
     * 归并排序
     * 将两个顺序序列合并成一个顺序序列的方法。
     * 如　设有数列{6，202，100，301，38，8，1}
     * 初始状态：6,202,100,301,38,8,1
     * 第一次归并后：{6,202},{100,301},{8,38},{1}，
     * 第二次归并后：{6,100,202,301}，{1,8,38}，
     * 第三次归并后：{1,6,8,38,100,202,301}
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        int n = arr.length;
        // 子数组的大小分别为1，2，4，8...
        // 刚开始合并的数组大小是1，接着是2，接着4....
        for (int i = 1; i < n; i += i) {
            //进行数组进行划分
            int left = 0;
            int mid = left + i - 1;
            int right = mid + i;
            //进行合并，对数组大小为 i 的数组进行两两合并
            while (right < n) {
                merge(arr, left, mid, right);
                left = right + 1;
                mid = left + i - 1;
                right = mid + i;
            }
            // 还有一些被遗漏的数组没合并，千万别忘了
            // 因为不可能每个字数组的大小都刚好为 i
            if (left < n && mid < n) {
                merge(arr, left, mid, n - 1);
            }
        }
        return arr;
    }

    // 合并函数，把两个有序的数组合并起来
    // arr[left..mif]表示一个数组，arr[mid+1 .. right]表示一个数组
    private static void merge(int[] arr, int left, int mid, int right) {
        //先用一个临时数组把他们合并汇总起来
        int[] a = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                a[k++] = arr[i++];
            } else {
                a[k++] = arr[j++];
            }
        }
        while (i <= mid) a[k++] = arr[i++];
        while (j <= right) a[k++] = arr[j++];
        // 把临时数组复制到原数组
        for (i = 0; i < k; i++) {
            arr[left++] = a[i];
        }
    }

    /**
     * 选择排序
     * 首先，找到数组中最小的元素，将它与第一个元素交换位置
     * 然后，再找出剩下的元素中最小的元素，将它与第二个元素交换位置
     * 直到最后一个元素。
     *
     * @param arr
     * @return
     */
    public static int[] selectSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return arr;
    }

    /**
     * 插入排序
     * 将数组中第二个元素取出，与左边第一个元素比较，如果这个元素比它大，则继续向左比较
     * 直到找到比它小的元素，将这个元素放到比他小的元素的右边第一个，如果没找到比他它小的，就放在第一个
     *
     * @param arr
     * @return
     */
    public static int[] insertSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int temp = arr[i];
            int index = i - 1;
            // 找到左边比它小的值的位置 index
            while (index > 0 && arr[index] > temp) {
                index--;
            }
            // 将index开始到循环中的这个值temp中间的这些值全部往右移动1位
            for (int j = i; j > index + 1; j--) {
                arr[j] = arr[j - 1];
            }
            // 将temp的值插入到找到的比它小的值的后面
            arr[index + 1] = temp;

        }
        return arr;
    }

    /**
     * 冒泡排序
     * 把第一个元素与第二个元素比较，如果第一个比第二个大，则交换他们的位置。接着继续比较第二个与第三个元素，如果第二个比第三个大，则交换他们的位置….
     * 我们对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样一趟比较交换下来之后，排在最右的元素就会是最大的数。
     * 除去最右的元素，我们对剩余的元素做同样的工作，如此重复下去，直到排序完成
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            boolean flag = true;
            // 一次遍历完成后，最后一位肯定比前面的值都大，所以这里循环的范围是0~n-i-1
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j + 1] < arr[j]) {
                    flag = false;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 如果flag为true，说明在刚才的循环中没有发现右边的值比左边的小的情况，
            // 也就是说已经完成了排序，后面的就不用循环了
            if (flag) {
                break;
            }
        }
        return arr;
    }

    /**
     * 希尔排序
     * 希尔排序的思想是采用插入排序的方法，先让数组中任意间隔为 m 的元素有序，
     * 刚开始 m 的大小可以是 m = n / 2,接着让 m = n / 4，
     * 让 m 一直缩小，当 m = 1 时，也就是此时数组中任意间隔为1的元素有序，此时的数组就是有序的了。
     *
     * @param arr
     * @return
     */
    public static int[] hillSortSort(int[] arr) {
        if (arr == null || arr.length < 2) return arr;
        int n = arr.length;
        // 对每组间隔为 h的分组进行排序，刚开始 h = n / 2;
        for (int h = n / 2; h > 0; h /= 2) {
            //对各个局部分组进行插入排序
            for (int i = h; i < n; i++) {
                // 将arr[i] 插入到所在分组的正确位置上
                insertI(arr, h, i);
            }
        }
        return arr;
    }

    /**
     * 将arr[i]插入到所在分组的正确位置上
     * 分组当中每个元素之间的下标差值为h
     */
    private static void insertI(int[] arr, int h, int i) {
        int temp = arr[i];
        int k;
        for (k = i - h; k > 0 && temp < arr[k]; k -= h) {
            arr[k + h] = arr[k];
        }
        arr[k + h] = temp;
    }

    public static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

}
