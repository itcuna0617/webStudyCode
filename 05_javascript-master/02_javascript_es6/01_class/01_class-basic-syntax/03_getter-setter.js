/* 03. getter, setter */

class Product {
    constructor(name, price) {
        
        /* 아래 두 줄은 각각 name과 price의 setter를 호출 */
        this.name = '전화기';
        this.price = 230000;
    }

    /* getter 함수(프로퍼티에 값을 읽을 때 동작(값 대입 안할 시)) */
    get name() {
        console.log('get name 동작');
        return this._name;
    }

    get price() {
        console.log('get price 동작');
        return this._price;
    }

    /* setter 함수(프로퍼티에 값을 쓸 때 동작(값을 대입 시)) */
    set name(value) {
        console.log('set name 동작');
        this._name = value;
    }

    set price(value) {
        console.log('set price 동작');
        this._price = value;
    }
}

let phone = new Product('전화기', 230000);  
console.log(phone);             // 실제 프로퍼티는 _name, _price 두개가 된다.
console.log(phone.name);        // name getter 호출
phone.name = '전화기2';         // name setter 호출
