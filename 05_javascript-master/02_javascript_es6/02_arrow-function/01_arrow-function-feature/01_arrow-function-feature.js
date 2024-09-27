/* 01. arrow-function-feature(화살표 함수의 특징) */

/* 1. 화살표 함수는 자체적으로 this를 가지지 않는다.(화살표 함수를 호출한 객체의 의미가 아니다.) */
/* 객체의 메소드 안에서 동일한 객체의 프로퍼티를 대상으로 콜백함수를 적용할 때 사용할 수 있다. */
let theater = {
    store: "건대점",
    titles: ["어벤져스", "공조2", "스파이더맨", "헐크", "라이온킹"],

    showMovieList() {
        console.log(this.store);
        /* forEach: Array에서 제공하는 메소드로 배열의 요소별로 할 기능을 콜백 함수로 넘기면 배열의 요소별로 돌아가며 실행함 */
        this.titles.forEach(
            title => console.log(this.store + ": " + title)
        );

        // this.titles.forEach(
        //     function(title) {
        //         console.log(this.store + ": " + title)
        //     }
        // );
    }
};

theater.showMovieList();

/* 2. 화살표 함수는 new와 함께 호출할 수 없다. */
const arrowFunc = () => {};

const normalFunc = function() {

};

// new arrowFunc();   // 에러발생, arrowFunc is not a constructor
new normalFunc();

/* 3. 화살표 함수는 arguments를 지원하지 않는다. */
let test = function() {
    console.log(arguments);
    const arrowFunc = () => console.log(arguments);
    // const arrowFunc = function() {
    //     console.log(arguments);
    // };
    arrowFunc(3, 4);
};

test(1, 2);

/* 
  화살표 함수는 다른 함수의 인수로 전달되어 콜백함수로 사용되는 경우가 많다.
  위와 같은 특징들은 콜백 함수 내부의 this가 외부 함수의 this와 다르기 때문에 발생하는 문제를 해결하기 위해
  의도적으로 설계 된 것이라 할 수 있다.(화살표 함수를 써야만 하는 경우가 존재한다.)
*/