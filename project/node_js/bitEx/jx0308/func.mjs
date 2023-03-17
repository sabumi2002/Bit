import{odd, even} from './valiables.mjs'
function checkOddOrEven(num){
    if(num%2){
        return odd;
    }
    return even;
}
export default checkOddOrEven;