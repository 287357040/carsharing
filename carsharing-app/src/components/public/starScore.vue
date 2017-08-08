<template>
    <div class="star">
        <div v-for="n in 5" v-bind:key="n">
            <div v-if="n < score">
                <span class="icon-start02" />
            </div>
            <div v-else>
                <div v-if="haflScorePosition == n">
                    <span class="icon-start03" />
                </div>
                <div v-else>
                    <span class="icon-start01" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        score: 0
    },
    computed: {
        haflScorePosition: function () {
            var temScore = this.$props.score;
            var halfScore = temScore;
            var count = 0;
            for (count = 0; temScore >= 0.5 && count <= 5; count++) {
                halfScore = temScore
                temScore -= count;
            }
            //console.log(halfScore >= 0.5 ? count : -1);
            return 1;
        }
    }
}
</script>

<style>

</style>
