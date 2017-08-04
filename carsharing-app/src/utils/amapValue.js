export const mapData = {
  amapInfo:{}
}
export const setMapInfo = (value) => mapData.amapInfo = value
export const getMapInfo = () => { return mapData.amapInfo }

/**
 * @@exports.default {Mixin}
 */
export default {
  data: () => ({
    mapData
  }),
  methods: {
    setMapInfo,
    getMapInfo
  }
}