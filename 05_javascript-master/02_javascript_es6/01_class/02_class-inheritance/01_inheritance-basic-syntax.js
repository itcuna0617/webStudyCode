/* 01. inheritance-basic-syntax(상속 기본 문법) */

class Animal {
    constructor(name, weight) {
        this.name = name;
        this.weight = weight;
    }

    eat(foodWeight) {
        this.weight += foodWeight;
        console.log(`${this.name}(은)는 ${foodWeight}kg의 식사를 하고 ${this.weight}kg이 되었습니다.`);
    }

    move(lostWeight) {
        if(this.weight > lostWeight) this.weight -= lostWeight;
        console.log(`${this.name}(은)는 움직임으로 인해 ${lostWeight}kg 감량되어 ${this.weight}kg이 되었습니다.`);
    }
}

let animal = new Animal("동물", 30);

animal.eat(1);
animal.move(0.5);

class Human extends Animal {

    /* 생성자를 생략하면 super()를 활용해 부모 constructor를 사용하게 된다. */
    // constructor(name, weight) {
    //     super(name, weight);
    // }

    /* 상속 받고 추가적으로 프로퍼티를 갖고 싶으면 아래와 같이 작성한다. */
    // constructor(name, weight, language) {
    //     super(name, weight);
    //     this.language = language;
    // }

    develop(language) {
        console.log(`${this.name}(은)는 ${language}로 개발을 합니다. 정말 즐겁습니다~`);
        // console.log(`${super.name}`);
        // super.eat(2);
    }
}

/* Animal에 정의 된 constructor 활용 가능 */
let human = new Human("수강생", 70, "Java");

/* Animal에 정의 된 메소드 접근 가능 */
human.eat(2);
human.move(1);

/* Human에 정의 된 메소드 접근 가능 */
human.develop("JavaScript");