/* 01. object-constructor-function (Object 생성자 함수) */
/* 
  new 연산자와 함께 Object 생성자 함수를 호출하면 빈 객체를 생성하여 반환한다.
  이후 프로퍼티(메소드 포함)들을 추가하여 객체를 완성할 수 있다.
*/

const student = Object();
// const student = {};
console.log(Object);    // Object()도 함수임을 확인

student.name = '유관순';
student.age = 16;

console.log(student);

/* 반드시 Object 생성자 함수를 사용해 객체를 생성할 필요는 없다. 경우에 따라 객체 리터럴을 사용하는 것이 더 간편하다. */
