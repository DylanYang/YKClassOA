/**
 * Created by dylanyang on 11/30/15.
 */
function delAllAccount(){
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
function delAllCourse(){
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
 * delAllAcc
 * @returns {boolean}
 */
function delAllAcc(){
    if(confirm('确定删除吗?')){
        return true;
    }else{
        return false;
    }
}