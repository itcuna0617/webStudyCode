/* 02. function-expression(함수 표현식) */
/*
  자바스크립트의 함수는 객체 타입의 값으로 값의 성질을 갖는 객체를 일급 객체라고 한다.
  함수는 일급 객체 이므로 함수 리터럴로 생성한 함수 객체를 변수에 할당할 수 있다.
*/

var hello = function (name){
    return `${name}님 안녕하세요!`;
};

console.log(hello('홍길동'));

var calc = function add(a, b){
    return a + b;
};

/* 함수 호출은 식별자로만 이루어진다. */
console.log(calc(10, 20));

/* 함수명으로는 호출이 불가능하다. */
console.log(add(10, 20));