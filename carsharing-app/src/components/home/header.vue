<template>
  <div class="navigator-wrapper linear-gradient-bg">
    <div class="navigator">
      <div class="nav-wrapper" :class ="{shadow:shadowActive}">
        <div  class="avatar-wrapper" @click="telephone_input">
          <div class="avatar">
            <i class="fa fa-user-o fa-lg"></i>
          </div>
        </div>
        <div class="tab-wrapper">
          <select class="passenger-owner-model">
            <option selected value="passenger">乘客</option>
            <option value="owner">车主</option>
          </select>
        </div>
        <div class="message-wrapper" >
          <div class="message">
              <i class="fa fa-commenting fa-lg"></i>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Icon from 'vue-awesome/components/Icon'
import { mapGetters, mapActions } from 'vuex'

export default {
  computed: {
    ...mapGetters([
      'shadowActive',

    ])
  },
  components: {
    Icon
  },
  methods: {
    ...mapActions([
      'telephone_input'
    ])
  }
}
</script>
<style>

</style>