/* 02. various-usage(다양한 사용법) */

let shirts = {
    productName: "베이직셔츠"
};

/* 객체에 존재하지 않는 프로퍼티는 기본 값을 설정해서 사용할 수 있다. */
/* 또한 콜론과 할당을 동시에 사용할 수 있다. */
let {productName: productName2 = "어떤 상품", color: color2 = "어떤 색상", price: price2 = 0} = shirts;

console.log(`productName2 : ${productName2}`);
console.log(`color2 : ${color2}`);
console.log(`price2 : ${price2}`);

/* 프로퍼티가 많은 복잡한 객체에서 원하는 정보만 뽑아오는 것도 가능하다.(이 경우가 주로 사용 됨) */
let pants = {
    productName: "배기팬츠",
    color: "검정색",
    price: 30000
};

let {productName: productName3} = pants;

console.log(`productName3 : ${productName3}`);

/* rest parameter ...로 나머지 요소를 한 번에 가져올 수도 있다. */

let {productName: productName4, ...rest} = pants;

console.log(`productName4 : ${productName4}`);
console.log(rest);
console.log(`rest.color : ${rest.color}`);
console.log(`rest.price : ${rest.price}`);

