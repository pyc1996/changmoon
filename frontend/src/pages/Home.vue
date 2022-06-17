<template>
  <div>
    <b-carousel
      id="carousel-1"
      v-model="slide"
      :interval="4000"
      controls
      indicators
      background="#ababab"
      img-width="1024"
      img-height="480"
      style="text-shadow: 1px 1px 2px #333;"
      @sliding-start="onSlideStart"
      @sliding-end="onSlideEnd"
    >

    <b-carousel-slide>
        <template class="pickgradient" #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            :src="backgroundImg[0]"
            alt="image slot"
            z-index="-2"
          />
        </template>
    </b-carousel-slide>

    <b-carousel-slide>
        <template class="pickgradient" #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            :src="backgroundImg[1]"
            alt="image slot"
            z-index="-2"
          />
        </template>
    </b-carousel-slide>

    <b-carousel-slide>
        <template class="pickgradient" #img>
          <img
            class="d-block img-fluid w-100"
            width="1024"
            height="480"
            :src="backgroundImg[2]"
            alt="image slot"
            z-index="-2"
          />
        </template>
    </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        slide: 0,
        sliding: null,
        backgroundImg:[
          require('@/assets/img/background/01_start.png'),
          require('@/assets/img/background/02_graph.png'),
          require('@/assets/img/background/03_buildings.png'),
        ]
      }
    },
    methods: {
      onSlideStart(slide) {
        this.sliding = true
      },
      onSlideEnd(slide) {
        this.sliding = false
      }
    }
  }
</script>