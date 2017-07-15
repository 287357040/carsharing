<template>
  <div class="navigator-wrapper">
    <div class="navigator">
      <div class="nav-wrapper" :class ="{shadow:shadowActive}">
        <div  class="avatar-wrapper" @click="telephone_input">
          <div class="avatar border-right-1px">
            <icon name="user"></icon>
          </div>
        </div>
        <div class="tab-wrapper">
          <div class="nav-mask mask-left"></div>
          <div class="nav-mask mask-right"></div>
          <div class="nav-mask">
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