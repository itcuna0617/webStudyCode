/* 01. array-and-object-copy(스프레드 문법을 이용한 배열, 객체 복사) */

/* 1. 배열 복사 */
let arr = [10, 30, 20];
let arrCopy = [...arr];          // 스프레드 문법을 활용한 깊은 복사

console.log(arr);
console.log(arrCopy);
console.log(arr === arrCopy);   // false 

arrCopy.push(50);
console.log(arr);
console.log(arrCopy);

/* 2. 객체 복사 */
let obj = {name : '홍길동', age : 20};
let objCopy = {...obj};

console.log(obj);
console.log(objCopy);
console.log(obj === objCopy);

objCopy.age = 30;
console.log(obj);
console.log(objCopy);
