package day0110;
// 사원 관리 프로그램을 작성하시오.
// 단, 사원 정보(사원 번호, 이름, 직급, 소속 부서, 연봉)은 하나의 구조체로 통제하고
// 사원 정보 입력, 출력은 별개의 메소드를 통하여 관리합니다.
public class Ex03Emp {
    public static void main(String[] args) {
        Employee employee = new Employee(1, "홍사범", "교육생", "비트", 9999);

        employee.printInfo();
    }


}
