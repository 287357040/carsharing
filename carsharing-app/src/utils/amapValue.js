// ��������,��Ϊ��ͼ�������ݵĴ�Ŵ�
export const mapData = {
  // amapInfo:{},
  startAmapInfo: {},    // 起始地址信息
  endAmapInfo: {},      // 终点地址信息
  transAmapInfo:{      // 统一返回对象 
    district: '', // 区县
    name: '', // 地址
    userNo: '',
    location: {
      lat:'',
      lng: ''
    }
  }    
}
// export const setMapInfo = (value) => mapData.amapInfo = value
// export const getMapInfo = () => { return mapData.amapInfo }
export const setStartMapInfo = (value) => mapData.startAmapInfo = value
export const getStartMapInfo = () => { 
  if(mapData.startAmapInfo.userNo === undefined){  //是存的选中后的地址
    return mapData.startAmapInfo
  } else{ // 从数据库查出的对象
      mapData.transAmapInfo.district = mapData.startAmapInfo.address
      mapData.transAmapInfo.name = mapData.startAmapInfo.name
      mapData.transAmapInfo.location.lat = mapData.startAmapInfo.latitude
      mapData.transAmapInfo.location.lng = mapData.startAmapInfo.longitude
      mapData.transAmapInfo.userNo = mapData.startAmapInfo.userNo
    return mapData.transAmapInfo
  }
}
export const setEndMapInfo = (value) => mapData.endAmapInfo = value
export const getEndMapInfo = () => { 
  if(mapData.endAmapInfo.userNo === undefined){ //是存的选中后的地址
    return mapData.endAmapInfo
  } else{  // 从数据库查出的对象
      mapData.transAmapInfo.district = mapData.endAmapInfo.address
      mapData.transAmapInfo.name = mapData.endAmapInfo.address
      mapData.transAmapInfo.location.lat = mapData.endAmapInfo.latitude
      mapData.transAmapInfo.location.lng = mapData.endAmapInfo.longitude
      return mapData.transAmapInfo
  }
}
// export const incLocationFlag = () => mapData.LocationFlag++
// export const getLocationFlag = () => { return mapData.LocationFlag }

/**
 * @exports.default {Mixin}
 */

export const driverRoutes = {
  routesInfo: {} //加入司机行程
}
export const saveRoutesInfo = (value) => { return driverRoutes.routesInfo = value };
export const getRoutesInfo = () => { return driverRoutes.routesInfo };


export default {
  data: () => ({
    mapData,
    driverRoutes
  }),
  methods: {
    // setMapInfo,
    // getMapInfo,
    setStartMapInfo,
    getStartMapInfo,
    setEndMapInfo,
    getEndMapInfo,
    // incLocationFlag,
    // getLocationFlag,
    saveRoutesInfo,
    getRoutesInfo
  }
}