/**
 * Created by dylanyang on 11/30/15.
 */
function delStu(){
    if(confirm('确定删除吗?')){
        //this.form.action("/delStudent");
        //this.form.submit();
        alert("yes");
        return true;
    }else{
        alert("no");
        return false;
    }
}
/**
 * delAllStu
 * @returns {boolean}
 */
function delAllStu(){
    if(confirm('确定删除吗?')){
        return true;
    }else{
        return false;
    }
}