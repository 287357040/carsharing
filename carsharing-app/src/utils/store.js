const STORAGE_KEY="carsharing"
export default{
    fetch: (key) => {
        return JSON.parse(window.localStorage.getItem(
            key||"[]"))
    },
    save: (key,items) => {
        window.localStorage.setItem(key,JSON.stringify(items))
    },
    isExisted:(key)=>{
     if(window.localStorage !== null || window.localStorage.getItem(key) !== null)
        {
            return true;
        }
     else
         return false;
    }
}