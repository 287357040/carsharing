const STORAGE_KEY="carsharing"
export default{
    fetch: (key) => {
        return JSON.parse(window.localStorage.getItem(
            key||"[]"))
    },
    save: (key,items) => {
        window.localStorage.setItem(key,JSON.stringify(items))
    },
    clear:()=>{
        //清理缓存个人信息
        window.localStorage.removeItem('user'||"[]");
        window.localStorage.removeItem('newDriverInfo'||"[]");
        window.localStorage.removeItem('newUserInfo'||"[]");
    },
    remove:(key) => {
        window.localStorage.removeItem(key)        
    }
}