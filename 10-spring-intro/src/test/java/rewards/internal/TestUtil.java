package rewards.internal;

public class TestUtil {

    //假设n无穷大
    //1 n居民 k感染者 p转运力 k - p会新增感染者 (k-p) <= 0 (k-p) >= n-p
    //2 n-p 居民 (k-p)*2 感染者 p-1转运力

    /**
     *
     * @param n 居民
     * @param k 感染者
     * @param p 转运力
     * @return
     */
    public static int calculateDays(int n,int k,int p){
        for (int i = p; i >=0 ; i--) {
            if(k <= i){
                return (p-i) + 1;
            }else {
                // 如果没有运完感染者，则第二天的感染者人数为新k
                k = (k-p)*2;
            }
        }
        // 0表示无法运送完成
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(calculateDays(10, 5, 4));
        System.out.println(calculateDays1(100, 10, 3));
    }

    public static int calculateDays1(int n, int k, int p) {
        int days = 0;
        int infected = k;
        while (infected > 0) {
            int transferred = Math.min(p, infected);
            infected -= transferred;
            if (infected == 0) {
                break;
            }
            infected += transferred;
            p--;
            infected = Math.min(infected * 2, n - infected); // 可能会转移出小区的感染者数量不能超过剩余感染者数量
            days++;
        }
        return days;
    }



}
