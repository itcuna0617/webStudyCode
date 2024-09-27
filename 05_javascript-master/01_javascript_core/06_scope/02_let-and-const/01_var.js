/* 01. var */

/* 1. 변수 중복 선언 허용 */
var msg = '안녕하세요';
console.log(msg);

/* var 키워드가 없는 것처럼 동작한다. */
var msg = '안녕히 가세요';
console.log(msg);

/* 초기화 문이 없는 중복 변수 선언은 무시된다. */
var msg;
console.log(msg);

/* 2. 함수 레벨 스코프 */
var i = 0;
for(var i = 0; i < 10; i++){}   // 함수내부가 아니면 전역 변수와 차이가 없는 스코프이다.
console.log(i); 

/* 3. 변수 호이스팅 */
/*
  var 키워드로 변수를 선언하면 변수 호이스팅에 의해 변수 선언문이 스코프의 선두로 끌어올려진 것처럼 동작한다.
  즉, 변수 선언문 이전에 참조할 수 있다.
  실행 시 오류가 발생하지는 않지만 이는 프로그램의 흐름에 맞지 않고 가독성을 떨어뜨리며 오류를 만들 여지가 있다.
*/
console.log(test);
test = '반갑습니다';
console.log(test);
var test;