public class ChoiceSort {

    public static void main(String[] args) {
        // 선택정렬 알고리즘
        int i, j, min;
        int index;
        int temp;

        int [] array = {1,10,5,8,7,6,4,3,2,9};

        for (i = 0; i < 10; i++) {
            min = 9999;
            index = i; // index 초기화

            for (j = i; j < 10; j++) { // j 증가시킴
                if(min > array[j]){
                    min = array[j];
                    index = j;
                }
            }
            // 가장 앞에 있는 것과 최솟값을 바꾸어주는 코드
            temp = array[i];
            array[i] = array[index];
            array[index] = temp;

            // 각 단계별로 정렬된 배열 출력
            System.out.print("Step " + (i+1) + ": ");
            for (int k = 0; k < 10; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();

        }
    }
}
