const{odd, even} = require('./variables');

function checkOddOrEven(num){
    if(num %2){
        return odd;
    }
    return even;
}
module.exports = checkOddOrEven;