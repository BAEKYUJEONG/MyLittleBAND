<template>
  <div>
    <h1>{{ todayTime }} <span>공연 일정</span></h1>
    <div class="flex-parent">
      <div v-if="shows.length == 0">예약된 공연이 없습니다</div>
      <div v-else class="input-flex-container">
        <div
          v-for="(show, index) in shows"
          :key="show.no"
          @click="clickItem(index)"
          class="input"
          :class="{active : show.actived}"
        >
          <span :data-time="show.startTime" :data-info="show.bandname"></span>
        </div>
      </div>
      <div class="description-flex-container">
        <p>And future Call of Duty players would thank them.</p>
        <p>Because every kid should get to be Tarzan for a day.</p>
        <p class="active">And the world rejoiced.</p>
        <p>Because building roads is inconvenient.</p>
        <p>Ain’t nobody got time to rake.</p>
        <p>Because paper currency is for noobs.</p>
        <p>Nobody likes cords. Nobody.</p>
        <p>Brighter than glow memory.</p>
        <p>To capitalize on an as-yet nascent market for cat photos.</p>
        <p>Because organic search rankings take work.</p>
      </div>
    </div>

    <!--
    <div style="position: absolute; bottom: 40px; right: 10px; font-size: 12px">
      <a href="https://codepen.io/cjl750/pen/XMyRoB" target="_blank"
        >original version with slinky mobile menu</a
      >
    </div>
    <div style="position: absolute; bottom: 15px; right: 10px; font-size: 12px">
      <a href="https://codepen.io/cjl750/pen/wdVxzV" target="_blank"
        >alternate version with custom range input</a
      >
    </div>
    -->
  </div>
</template>

<script>
/*
$(function() {
  var inputs = $(".input");
  var paras = $(".description-flex-container").find("p");
  inputs.click(function() {
    var t = $(this),
      ind = t.index(),
      matchedPara = paras.eq(ind);

    t.add(matchedPara).addClass("active");
    inputs
      .not(t)
      .add(paras.not(matchedPara))
      .removeClass("active");
  });
});
*/

export default {
  data() {
    return {
      // sample datas
      todayTime: "2021-03-24",
      shows: [
        {
          no: 1,
          title: "공연1",
          startTime: "14:00",
          bandname: "밴드1",
          actived: false,
        },
        {
          no: 2,
          title: "공연2",
          startTime: "16:00",
          bandname: "밴드2",
          actived: false,
        },
        {
          no: 3,
          title: "공연3",
          startTime: "19:00",
          bandname: "밴드3",
          actived: false,
        },
        {
          no: 4,
          title: "공연4",
          startTime: "20:00",
          bandname: "밴드4",
          actived: false,
        },
        {
          no: 5,
          title: "공연5",
          startTime: "22:00",
          bandname: "밴드5",
          actived: false,
        },
      ],
    };
  },
  methods: {
    clickItem(key) {
      this.shows.map((value, index) => {
          if(index == key)  value.actived = true;
          else              value.actived = false;
      });
    },
  },
};
</script>

<style>
html,
body {
  height: 100%;
}

body {
  font-family: "Quicksand", sans-serif;
  font-weight: 500;
  color: #424949;
  background-color: #ecf0f1;
  padding: 0 25px;
  display: flex;
  flex-direction: column;
  position: relative;
}

h1 {
  text-align: center;
  height: 38px;
  margin: 60px 0;
}
h1 span {
  white-space: nowrap;
}

.flex-parent {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
}

.input-flex-container {
  display: flex;
  justify-content: space-around;
  align-items: center;
  width: 80vw;
  height: 100px;
  max-width: 1000px;
  position: relative;
  z-index: 0;
}

.input {
  width: 25px;
  height: 25px;
  background-color: #2c3e50;
  position: relative;
  border-radius: 50%;
}
.input:hover {
  cursor: pointer;
}
.input::before,
.input::after {
  content: "";
  display: block;
  position: absolute;
  z-index: -1;
  top: 50%;
  transform: translateY(-50%);
  background-color: #2c3e50;
  width: 5vw;
  height: 5px;
  max-width: 50px;
}
.input::before {
  left: calc(-4vw + 12.5px);
}
.input::after {
  right: calc(-4vw + 12.5px);
}
.input.active {
  background-color: #2c3e50;
}
.input.active::before {
  background-color: #2c3e50;
}
.input.active::after {
  background-color: #aeb6bf;
}
.input.active span {
  font-weight: 700;
}
.input.active span::before {
  font-size: 13px;
}
.input.active span::after {
  font-size: 15px;
}
.input.active ~ .input,
.input.active ~ .input::before,
.input.active ~ .input::after {
  background-color: #aeb6bf;
}
.input span {
  width: 1px;
  height: 1px;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  visibility: hidden;
}
.input span::before,
.input span::after {
  visibility: visible;
  position: absolute;
  left: 50%;
}
.input span::after {
  content: attr(data-time);
  top: 25px;
  transform: translateX(-50%);
  font-size: 14px;
}
.input span::before {
  content: attr(data-info);
  top: -65px;
  width: 70px;
  transform: translateX(-5px) rotateZ(-45deg);
  font-size: 12px;
  text-indent: -10px;
}

.description-flex-container {
  width: 80vw;
  font-weight: 400;
  font-size: 22px;
  margin-top: 100px;
  max-width: 1000px;
}
.description-flex-container p {
  margin-top: 0;
  display: none;
}
.description-flex-container p.active {
  display: block;
}

@media (min-width: 1250px) {
  .input::before {
    left: -37.5px;
  }

  .input::after {
    right: -37.5px;
  }
}
@media (max-width: 850px) {
  .input {
    width: 17px;
    height: 17px;
  }
  .input::before,
  .input::after {
    height: 3px;
  }
  .input::before {
    left: calc(-4vw + 8.5px);
  }
  .input::after {
    right: calc(-4vw + 8.5px);
  }
}
@media (max-width: 600px) {
  .flex-parent {
    justify-content: initial;
  }

  .input-flex-container {
    flex-wrap: wrap;
    justify-content: center;
    width: 100%;
    height: auto;
    margin-top: 15vh;
  }

  .input {
    width: 60px;
    height: 60px;
    margin: 0 10px 50px;
    background-color: #aeb6bf;
  }
  .input::before,
  .input::after {
    content: none;
  }
  .input span {
    width: 100%;
    height: 100%;
    display: block;
  }
  .input span::before {
    top: calc(100% + 5px);
    transform: translateX(-50%);
    text-indent: 0;
    text-align: center;
  }
  .input span::after {
    top: 50%;
    transform: translate(-50%, -50%);
    color: #ecf0f1;
  }

  .description-flex-container {
    margin-top: 30px;
    text-align: center;
  }
}
@media (max-width: 400px) {
  body {
    min-height: 950px;
  }
}
</style>
