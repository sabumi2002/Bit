# 알고리즘 함수 정리

# ➕ Data Type

---

## String

- `startsWith(str)` : 문자열이 특정 문자로 시작되는지 판별
- `endsWith(str)` : 문자열이 특정 문자로 끝나는지 판별
- `equal(str)` : String 문자열 값 비교
- `indexOf(str)` : 특정 문자열이 대상 문자열의 몇 번째 인덱스에 위치하는지 반환
    - 특정 문자열이 없을 경우에는 -1을 리턴
- `substring` : 지정한 범위에 속하는 문자열 반환
    - `substring(index)`: index 위치를 포함하여 이후의 모든 문자열을 리턴
    - `substring(beginIndex, endIndex)` : beginIndex에서 endIndex-1까지의 부분 문자열을 반환
- `replace(beforeStr, afterStr)` : 특정 문자열을 새로운 문자열로 치환
- `toLowerCase(str)` : 문자열을 소문자로 변환
- `toUpperCase(str)` : 문자열을 대문자로 변환
- `trim(str)` : 문자열의 앞뒤 공백 제거
    - 단, 문자열 내부의 공백은 `replace(" ", "")`를 사용해야 함
- `charAt(index)` : 문자열 특정 위치에 있는 문자 반환
    - 인덱스 값으로 마이너스 값을 대입하거나, 문자열 길이보다 큰 인덱스 값을 대입하면 `java.lang.StringIndexOutOfBoundsException` 오류 발생
- `String.valueOf(str)` : 지정된 값을 String으로 변환
- `contains(str)` : 특정 문자열이 포함되어 있는지 확인
- `split(regex)` : 문자열을 특정 문자열을 기준으로 나는 후 배열을 반환
- `length` : 문자열의 길이를 반환

## Arrays

- `Arrays.sort()` : 오름차순으로 정렬
    
    > 기본 정렬조건이 오름차순인 이유는 Class 내에 기본적으로 구현되어있는 Comparable Interface의 compareTo 메서드를 기준으로 하기 때문이다. Java에서 인스턴스를 서로 비교하는 클래스들은 모두 Comparable 인터페이스가 구현되어 있다.
    > 
    - 정렬 대상 범위를 지정
        
        ```
        int[] intArr = new int[] {1, 3, 5, 2, 4};
        Arrays.sort(intArr, 2, 5); // intArr[2]~intArr[4]의 값만 정렬
        ```
        
    - 내림차순으로 정렬
        - Wrapper Class로 된 배열(Reference Type Array)만 가능하다.
        - 이미 정의된 메소드를 활용하여 정렬할 수 있다.`Comparator.reverseOrder()``Collections.reverseOrder()`
            
            ```
            Integer[] integerArr = new Integer[] {1,3,5,2,4};
            String[] stringArr = new String[] {"A","C","B","E","D"};
            
            Arrays.sort(integerArr,Comparator.reverseOrder()); //내림차순
            Arrays.sort(stringArr,Collections.reverseOrder()); //내림차순
            ```
            
- `Arrays.asList(arr)` : 배열을 ArrayList로 변환
- `Arrays.fill(arr, value)` : 배열을 value 값으로 채움
    - `Arrays.fill(arr, start, end, value)` : 배열의 start부터 end-1까지 value 값으로 채움
- `Arrays.copyOf(arr, size)` : 배열의 0번째 원소부터 size만큼 복사 (새로운 배열 생성)
- `Arrays.copyOfRange(arr, start, size)` : 배열의 start원소부터 size만큼 복사 (새로운 배열 생성)

## 문자형 → 숫자형

- Integer, Double, Float 형은 각 클래스에 정의된 `valueOf()` 메소드를 통해 변환할 수 있다.
    - `Integer.valueOf(str)`
    - `Double.valueOf(str)`
    - `Float.valueOf(str)`
- Integer, Long, Short 형은 각 클래스에 정의된 `parse{자료형}()` 메소드를 통해 변환할 수 있다.
    - `Integer.parseInt(str)`
    - `Long.pareLong(str)`
    - `Short.parseShor(str)`

## 숫자형 → 문자형

- String 클래스에 정의된 `valueOf()` 메소드를 통해 변환할 수 있다.
    - `String.valueOf(number)`
- Integer, Double, Float 형은 각 클래스에 정의된 `toString()` 메소드를 통해 변환할 수 있다.
    - `Interger.toString(number)`
    - `Float.toString(number)`
    - `Double.toString(number)`

## 정수형 ↔ 실수형

- 강제 형변환(casting)을 통해 변환할 수 있다.
    - `(int)number` , `(double)number` , `(float)number`

## Array ↔ List

> Array와 List는 항상 반복문을 통해 변환할 수 있다.
> 

### (Reference Type) Array → List

- `Arrays.asList(arr)`
    - 고정 길이(fixed-size)인 원본 배열의 **list view**를 반환한다.
    - 변환된 리스트에 값을 추가하는 것이 불가능하다.
    - 원본 배열의 값을 변경하면 리스트 값도 같이 변경된다.
- `new ArrayList<>(Arrays.asList(arr))`
    - list view를 가지고 새로운 리스트 객체를 만든 것이다.
    - 리스트에 값을 추가할 수 있다.
    - 원본 배열의 값과 동기화하지 않는다.
- [Java 8 이후] Stream 사용하기
    - `Stream.of(arr).collect(Collectors.toList())`
    - Stream의 `collect()` 메소드는 데이터를 원하는 타입으로 변경해준다.

### (Primitive Type) Array → List

- 반복문 사용하기
    
    ```
    // int -> List
    List<Integer> intList = new ArrayList<>();
    for (int element : arr) {
    	intList.add(element);
    }
    ```
    
- Stream 사용하기
    
    ```
    // int -> List
    List<Integer> intList = Arrays.stream(arr)
    							  .boxed()
    							  .collect(Collectors.toList());
    ```
    
    - Stream의 `boxed()` 메소드는 Primitive Stream 값을 Wrapper 값으로 바꿔준다.
    - 이후 `collect()` 메소드를 통해 원하는 타입으로 변경할 수 있다.

### List → Array

- `arrList.toArray(new String[arrList.size()])`
    - 파라미터로 전달 받은 배열 객체의 길이기 원본 리스트보다 작은 경우, 자동으로 원본 리스트의 size 크기로 배열을 만들어준다.
    - 원본 리스트의 길이보다 배열의 크기를 더 크게 지정하면, 나머지 인덱스는 `null` 로 채워진다.

# ➕ Collection Framework

---

![https://blog.kakaocdn.net/dn/mjVFA/btqZBcPCt5e/iwtcUaOcIBEQiCRXIvqEjK/img.jpg](https://blog.kakaocdn.net/dn/mjVFA/btqZBcPCt5e/iwtcUaOcIBEQiCRXIvqEjK/img.jpg)

## Collections

- `Collections.sort(c)` : 컬렉션 객체를 오름차순으로 정렬
- `Collections.max(c)` : 컬렉션 내의 최댓값 반환
- `Collections.min(c)` : 컬렉션 내의 최솟값 반환
- `Collections.reverse(c)` : 컬렉션을 순서를 역으로 변경
- `Collections.copy(fromObj, toObj)` : fromObj의 모든 객체를 toOBj으로 복사해 반환
- `Collections.bianrySearch(c, obj)` : **정렬된** 컬렉션에서 이진 탐색으로 특정 객체를 찾아 인덱스를 반환
- `Collections.disjoint(c1, c2)` : 주어진 두 컬렉션에 일치하는 값이 하나도 없으면 true, 하나라도 있으면 false 반환 (= **서로소 집합**)

## Iterator

> Collection 인터페이스에서는 Iterator 인스턴스를 반환하는 iterator() 메소드를 정의하여 각 요소에 접근하도록 하고 있다. 따라서 Collection 인터페이스를 상속 받는 List와 Set 인터페이스에서도 iterator() 메소드를 사용할 수 있다.
> 
- `hasNext()` : 해당 이터레이션(iteration)이 다음 요소를 가지고 있으면 true를 반환하고, 더 이상 다음 요소를 가지고 있지 않으면 false를 반환
- `next()` : 이터레이션(iteration)의 다음 요소를 반환

## Collection 인터페이스 공통

- `equals(c)` : 해당 컬렉션과 전달된 객체가 같은지 확인
- `isEmpty()` : 해당 컬렉션 객체가 비어있는지 확인
- `size()` : 해당 컬렉션 객체의 크기 반환
- `iterator()` : 해당 컬렉션의 반복자(iterator)를 반환
- `toArray(c)` : 해당 컬렉션의 모든 요소를 Object 타입의 배열로 반환

## List

List 인터페이스를 구현한 **ArrayList**를 주로 사용

- `add` : 특정 값 추가
    - `add(obj)` : 리스트 끝에 원소 추가
    - `add(index, obj)` : 특정 인덱스에 원소 추가
- `addAll` : 주어진 컬렉션의 모든 객체를 추가
    - `addAll(c)` : 주어진 컬렉션의 모든 객체를 끝에 추가
    - `addAll(index, c)` : 주어진 컬렉션의 모든 객체를 index 위치에 추가
- `get(index)` : 특정 인덱스 값 조회
- `set(index, obj)` : 특정 위치에 객체 저장
- `remove` : 특정 값 삭제
    - `remove(index)` : 인덱스에 위치하는 값 삭제
    - `remove(obj)` : 특정 객체 삭제
- `indexOf(obj)` : 리스트에서 특정 객체의 인덱스를 반환
- `subList(fromIndex, toIndex)` : fromIndex와 toIndex-1까지의 부분 리스트 반환
- `contains(obj)` : 특정 원소가 리스트 내에 있는 지 확인
- `sort()` : 리스트를 오름차순으로 정렬
    - `sort(comparator)` : 리스트를 특정 정렬 기준으로 정렬
- `toArray()` : 리스트를 고정 크기의 배열로 전환

## Map

Map 인터페이스를 구현한 **HashMap**을 주로 사용

- `put(key, value)` : 맵에 key와 value 값 추가
- `putAll(map)` : map의 모든 원소를 추가
- `get(key)` : key와 매핑된 value 반환
    - 만약 key 값이 없을 땐, null을 반환
- `remove(key)` : 특정 key에 해당하는 값을 삭제
- `replace(key, value)` : 특정 key에 해당하는 값을 value로 대체
- `clear()` : 맵에 저장된 모든 객체 제거
- `containsKey(key)` : 특정 key가 맵에 있는지 판별
- `containsValue(value)` : 특정 value가 맵에 있는지 판별
- `keySet()` : 맵의 모든 key가 저장된 Set을 반환
- `entrySet()` : 맵의 모든 entry(=key와 value의 결합) 저장된 Set을 반환
- `values()` : 맵의 모든 value 값을 컬랙션 형태로 반환

## Set

Set 인터페이스를 구현한 **HashSet**을 주로 사용

- `clear()` : Set에 저장된 모든 객체 제거
- `getOrDefault(key, defaultValue)` : key가 없다면 defualtValue로 초기화하여 반환하고, 있다면 해당 key의 value를 반환

> 아래 함수들은 확인/성공 여부를 boolean 값으로 반환
> 
- `add(obj)` : Set에 특정 요소 추가
- `addAll(c)` : 주어진 컬렉션의 모든 객체를 집합에 추가 (=**합집합**)
- `remove(obj)` : Set에 특정 요소 삭제
- `removeAll(c)` : 주어진 컬렉션의 객체와 같은 원소를 제거(=**차집합**)
- `contains(obj)` : 특정 객체를 포함하는지 확인
- `containsAll(c)` : 주어진 컬렉션의 모든 객체를 포함하는지 확인(=**부분집합**)
- `retainAll(c)` : 주어진 컬렉션의 객체와 동일한 것만 남기고 삭제(=**교집합)**

## Stack

- `push(obj)` : 스택의 top에 값을 삽입
- `pop()` : 스택의 top 값을 반환한 뒤에 삭제
- `peek()` : 스택의 top 값 조회
- `clear()` : 스택의 값을 모두 제거
- `empty()` : 스택이 비어있는지 확인
- `contains(obj)` : 스택이 특정 원소를 포함하고 있는지 확인
- `search(obj)` : 스택에서 특정 원소를 찾아 위치(1부터 시작)를 반환
    - 원소가 스택에 없다면 -1을 반환

## Queue

일반적인 큐는 주로 **LinkedList**를 사용

- `add(obj)` : LinkedList 의 마지막에 객체를 추가
- `offer(obj)` : 큐의 마지막에 객체를 추가
    - 큐의 크기가 꽉 찼을 경우에 `add`는 예외를 발생시키지만 `offer`는 false를 반환
    - *`offerFirst(obj)` : LinkedList의 맨 앞에 객체를 추가*
    - *`offerLast(obj)` : LinkedList의 맨 마지막에 객체를 추가*
- `poll()` : 큐의 맨 앞에 위치한 값을 반환한 뒤에 삭제
    - *`pollFirst()` : LinkedList의 첫번째 노드르 반환하면서 제거*
    - *`pollLast()` : LinkedList의 마지막 노드르 반환하면서 제거*
- `peek()` : 큐의 맨 앞에 위치한 값을 반환
    - *`peekFirst()` : LinkedList의 첫번째 노드를 반환*
    - *`peekLast()` : LinkedList의 마지막노드를 반환*

우선순위 큐는 주로 **PriorityQueue**를 사용

- LinkedList로 구현하는 큐와 사용하는 메소드가 거의 유사

# ➕ Math

---

- `Math.max(n1, n2)` : 두 인자 중 더 큰 값을 반환
- `Math.min(n1, n2)` : 두 인자 중 더 작은 값을 반환
- `Math.abs(n)` : 절댓값을 반환
- `Math.pow(base, exponent)` : 제곱 값을 반환
    - 앞의 인자는 밑, 뒤의 인자는 지수를 뜻함
- `Math.sqrt(n)` : 제곱근 반환
- `Math.round(n)` : 소숫점 첫번째 자리에서 반올림한 결과 반환
- `Math.floor(n)` : 내림 연산 결과 반환
- `Math.ceil(n)` : 올림 연산 결과 반환

# ➕ References

---

- Arrays
    - 참고 링크: [[Java] 자바 배열복사하는 방법을 알아보자! Arrays.copyOf](https://seeminglyjs.tistory.com/215)
    - 참고 링크: [자바 Arrays.fill()](https://zetawiki.com/wiki/%EC%9E%90%EB%B0%94_Arrays.fill())
- Data Type 변환
    - 참고 링크: [[Java] 형변환 Casting(문자형,정수형,실수형)](https://coding-factory.tistory.com/130)
    - 참고 링크: [[Java] 배열을 List로, List를 배열로 변환하기](https://hianna.tistory.com/551)
    - 참고 링크: [https://hianna.tistory.com/552](https://hianna.tistory.com/552)
- Collection Framework
    - 참고 링크: [Java - Collection과 Map의 종류(List, Set, Map)](https://memostack.tistory.com/234)
    - 참고 링크: [01. [자바] 컬렉션 프레임워크(Collections Framework)](https://staticclass.tistory.com/96)
    - 참고 링크: [java.util.Collections 주요 메소드 [1/1]](https://codevang.tistory.com/139)
- Stack
    - 참고 링크: [[Java] 자바 Stack 클래스 사용법 & 예제 총정리](https://coding-factory.tistory.com/601)
- Math
    - 참고 링크: [[JAVA] 자바 - Math 클래스 (수학 관련 처리 클래스)](https://lktprogrammer.tistory.com/114)