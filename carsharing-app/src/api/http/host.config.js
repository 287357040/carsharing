/**
 * Created by lmz on 2017/7/11.
 */
const IP = 'http://192.168.153.157:8080';
export const API_ROOT = (process.env.NODE_ENV === 'production') ? IP : IP;

