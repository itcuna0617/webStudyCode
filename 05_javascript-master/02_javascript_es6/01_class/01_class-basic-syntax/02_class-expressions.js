/* 02. class expressions(클래스 표현식) */

/* 1. 익명 클래스 표현식 */
let Tutor = class {
    teach() {
        console.log('이해하셨나요~?');
    }
};

new Tutor().teach();
console.log(Tutor);

/* 2. 기명 클래스 표현식 */
let Tutee = class MyTutee {
    learn() {
        console.log('우와~ 이해했어요!');
        console.log(MyTutee);       // 내부에선 에러가 안남
    }
};

new Tutee().learn();

// console.log(MyTutee);            // 외부에선 에러 발생

/* 3. 클래스 동적 생성(메소드 호출 시 생성) */
function makeTutee(message) {
    return class {
        feedback() {
            console.log(message);
        }
    };
}

let SecondTutee = makeTutee("100점이예요!");
new SecondTutee().feedback();

/* 클래스도 함수처럼 일급 객체이며 다른 표현식 내부에서 정의, 전달, 반환, 할당이 가능하다. */