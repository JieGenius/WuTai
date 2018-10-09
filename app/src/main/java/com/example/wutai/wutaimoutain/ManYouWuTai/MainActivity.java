package com.example.wutai.wutaimoutain.ManYouWuTai;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.wutai.wutaimoutain.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.list_view)
    ListView list;
    FloatingActionButton fbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wutai_main);
        ButterKnife.bind(this);
        list = findViewById(R.id.list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Data data = new Data();
        data.title="北台：状似斗杓，高峻插天";
        data.abst="北台号称“华北屋脊”，名叶斗峰，最高海拔3058米。从东台去往北台顶，步行的距离只有14公里，不过，应了“望山跑死马”这句话，我们足足走了5个小时。半路上会遇到星星两两的徒步人，大家相遇总是会面带微笑的打个招呼，也是互相激励的一种方式。这段路算是整个大朝台中最好走的了，在半路上的一个小坡顶可以望到台怀镇白塔寺的大白塔，还有丝丝升起的炊烟。一路上我们几个人拍点花、聊着天、看看牛羊就到了华北屋脊的牌坊处，坐下来大家把自己准备的食物分而食之。\n" +
                "\n" +
                " 这时，突然跑过来几只狐狸，没怎么见过大型野生动物的我们，也着实吓了一跳。它们倒是淡定很多，开始摇尾巴卖萌，盯着我手里的火腿肠。看这些狐狸的身材，也是被大朝台的游客们喂到了发福。\n" +
                "\n" +
                " 当我登顶北台的时候，虽是炎炎夏日，但吹在身上的风却是刺骨寒冷。看看周围的僧侣们，无一例外的裹着大棉袄，我心想那要是寒冬该怎么度过？想起了中国的一位旅游鼻祖，徐霞客。那一年是公元1633年，48岁的徐霞客登上了五台山，正值深秋。他在《徐霞客游记》中写到：“初六日风怒起，滴水成冰”。鲜花绿草估计徐霞客是看不到了，不过白雪皑皑对于他这个南方人，也算是一种体验。说话间，就听到滚滚的雷声，但北台顶没有下雨，这雨云飘在我们脚下。我们此时站在云雾之上，脚下是云雷滚滚，仿佛就像从天上看向人间。\n";
        data.name="张四";
        data.num_commu="3521";
        data.num_read="25122";
        data.head="";
        data.pic="http://139.199.37.80/Mount_WuTai/upload/0.jpg";
        Data data1 = new Data();
        data1.title="-20度 “华北屋脊”五台山顺时针风雪大朝台\n";
        data1.abst="五台山站很小，凌晨2点的五台山站还是有点冷，找了家住宿稍微休息，买了两个气罐。\n" +
                "几乎没有睡，小眯了一会时钟到了凌晨5点，零下十几度的五台山站还是很冷的，全身武装出门等待包车师傅。\n" +
                "五台山站到鸿门岩不到一小时的车程，凌晨6点多抵达朝台的起点鸿门岩，当下气温零下13度，冻得很没有稍加调整立马摸黑开始徒步前往东台。\n" +
                "沿着鸿门岩向上一直向山顶则可抵达东台顶。\n" +
                "鸿门岩到东台，一路上坡拔高，累！因摸黑又冷没有走捷径小路上切，只沿着盘山路一直上坡约7点就抵达到东台。\n";
        data1.name="张五";
        data1.num_commu="4421";
        data1.num_read="2241";
        data1.head="2222";
        data1.pic="http://139.199.37.80/Mount_WuTai/upload/1.jpg";
        Data data2 = new Data();
        data2.title="东台：东望明霞，如波似镜";
        data2.abst="从北京站坐上前往五台山的夜班火车，大约凌晨4点就到达五台山站。火车站外，是此起彼伏的司机拉客的吆喝声，换乘小巴，在上下颠簸、左右盘旋的山路上开上一个来钟，便抵达鸿门岩。这里是东台和北台之间的一个垭口，也是此次大朝台徒步之行开始的地方。"+
                "从鸿门岩前往东台望海峰，去欣赏五台山的云海和日出。路途很近，慢慢走的话30分钟也到了，不过眼瞅着东边天空开始泛红，太阳马上就要蹦出来，我们加快了步伐。高山草甸上有前人踩出来的小路，户外运动者管这种“不走大路，超近道走小路”的行为叫做“切”。大路毕竟是坦荡多了，小路上不仅坑洼，最恐怖的是可能踩到牛屎。\n" +
                "\n" +
                "后来发现，我们这大朝台一路都在“切”，也着实踩了不少的牛屎。好在这些散养的牛，吃青草喝山泉，排泄物看着也是不那么令人作呕，闻起来有一种切割青草的味道。到了东台望海寺，站在露台上往东方望去，太阳已经挤出了半个身子，蒸腾的雾气往上蹿涌，早霞还有点刺眼，连绵不绝的青山中时不时回荡着鸟鸣。我们在望海寺稍作休整，擦好防晒霜，便向北台进发。\n";
        data2.name="张六";
        data2.num_commu="2244";
        data2.num_read="4422";
        data2.head="";
        data2.pic="http://139.199.37.80/Mount_WuTai/upload/2.jpg";
        Data data3 = new Data();
        data3.title="北台：状似斗杓，高峻插天";
        data3.abst="北台号称“华北屋脊”，名叶斗峰，最高海拔3058米。从东台去往北台顶，步行的距离只有14公里，不过，应了“望山跑死马”这句话，我们足足走了5个小时。半路上会遇到星星两两的徒步人，大家相遇总是会面带微笑的打个招呼，也是互相激励的一种方式。这段路算是整个大朝台中最好走的了，在半路上的一个小坡顶可以望到台怀镇白塔寺的大白塔，还有丝丝升起的炊烟。一路上我们几个人拍点花、聊着天、看看牛羊就到了华北屋脊的牌坊处，坐下来大家把自己准备的食物分而食之。\n" +
                "\n" +
                " 这时，突然跑过来几只狐狸，没怎么见过大型野生动物的我们，也着实吓了一跳。它们倒是淡定很多，开始摇尾巴卖萌，盯着我手里的火腿肠。看这些狐狸的身材，也是被大朝台的游客们喂到了发福。\n" +
                "\n" +
                " 当我登顶北台的时候，虽是炎炎夏日，但吹在身上的风却是刺骨寒冷。看看周围的僧侣们，无一例外的裹着大棉袄，我心想那要是寒冬该怎么度过？想起了中国的一位旅游鼻祖，徐霞客。那一年是公元1633年，48岁的徐霞客登上了五台山，正值深秋。他在《徐霞客游记》中写到：“初六日风怒起，滴水成冰”。鲜花绿草估计徐霞客是看不到了，不过白雪皑皑对于他这个南方人，也算是一种体验。说话间，就听到滚滚的雷声，但北台顶没有下雨，这雨云飘在我们脚下。我们此时站在云雾之上，脚下是云雷滚滚，仿佛就像从天上看向人间。\n";
        data3.name="555555";
        data3.num_commu="5";
        data3.num_read="2";
        data3.head="";
        data3.pic="http://139.199.37.80/Mount_WuTai/upload/3.jpg";

        Data data4 = new Data();
        data4.title="北台：状似斗杓，高峻插天";
        data4.abst="北台号称“华北屋脊”，名叶斗峰，最高海拔3058米。从东台去往北台顶，步行的距离只有14公里，不过，应了“望山跑死马”这句话，我们足足走了5个小时。半路上会遇到星星两两的徒步人，大家相遇总是会面带微笑的打个招呼，也是互相激励的一种方式。这段路算是整个大朝台中最好走的了，在半路上的一个小坡顶可以望到台怀镇白塔寺的大白塔，还有丝丝升起的炊烟。一路上我们几个人拍点花、聊着天、看看牛羊就到了华北屋脊的牌坊处，坐下来大家把自己准备的食物分而食之。\n" +
                "\n" +
                " 这时，突然跑过来几只狐狸，没怎么见过大型野生动物的我们，也着实吓了一跳。它们倒是淡定很多，开始摇尾巴卖萌，盯着我手里的火腿肠。看这些狐狸的身材，也是被大朝台的游客们喂到了发福。\n" +
                "\n" +
                " 当我登顶北台的时候，虽是炎炎夏日，但吹在身上的风却是刺骨寒冷。看看周围的僧侣们，无一例外的裹着大棉袄，我心想那要是寒冬该怎么度过？想起了中国的一位旅游鼻祖，徐霞客。那一年是公元1633年，48岁的徐霞客登上了五台山，正值深秋。他在《徐霞客游记》中写到：“初六日风怒起，滴水成冰”。鲜花绿草估计徐霞客是看不到了，不过白雪皑皑对于他这个南方人，也算是一种体验。说话间，就听到滚滚的雷声，但北台顶没有下雨，这雨云飘在我们脚下。我们此时站在云雾之上，脚下是云雷滚滚，仿佛就像从天上看向人间。\n";
        data4.name="555555";
        data4.num_commu="5";
        data4.num_read="2";
        data4.head="";
        data4.pic="http://139.199.37.80/Mount_WuTai/upload/4.jpg";

        Data data5 = new Data();
        data5.title="北台：状似斗杓，高峻插天";
        data5.abst="北台号称“华北屋脊”，名叶斗峰，最高海拔3058米。从东台去往北台顶，步行的距离只有14公里，不过，应了“望山跑死马”这句话，我们足足走了5个小时。半路上会遇到星星两两的徒步人，大家相遇总是会面带微笑的打个招呼，也是互相激励的一种方式。这段路算是整个大朝台中最好走的了，在半路上的一个小坡顶可以望到台怀镇白塔寺的大白塔，还有丝丝升起的炊烟。一路上我们几个人拍点花、聊着天、看看牛羊就到了华北屋脊的牌坊处，坐下来大家把自己准备的食物分而食之。\n" +
                "\n" +
                " 这时，突然跑过来几只狐狸，没怎么见过大型野生动物的我们，也着实吓了一跳。它们倒是淡定很多，开始摇尾巴卖萌，盯着我手里的火腿肠。看这些狐狸的身材，也是被大朝台的游客们喂到了发福。\n" +
                "\n" +
                " 当我登顶北台的时候，虽是炎炎夏日，但吹在身上的风却是刺骨寒冷。看看周围的僧侣们，无一例外的裹着大棉袄，我心想那要是寒冬该怎么度过？想起了中国的一位旅游鼻祖，徐霞客。那一年是公元1633年，48岁的徐霞客登上了五台山，正值深秋。他在《徐霞客游记》中写到：“初六日风怒起，滴水成冰”。鲜花绿草估计徐霞客是看不到了，不过白雪皑皑对于他这个南方人，也算是一种体验。说话间，就听到滚滚的雷声，但北台顶没有下雨，这雨云飘在我们脚下。我们此时站在云雾之上，脚下是云雷滚滚，仿佛就像从天上看向人间。\n";
        data5.name="555555";
        data5.num_commu="5";
        data5.num_read="2";
        data5.head="";
        data5.pic="http://139.199.37.80/Mount_WuTai/upload/5.jpg";
        Data data6 = new Data();
        data6.title="北台：状似斗杓，高峻插天";
        data6.abst="北台号称“华北屋脊”，名叶斗峰，最高海拔3058米。从东台去往北台顶，步行的距离只有14公里，不过，应了“望山跑死马”这句话，我们足足走了5个小时。半路上会遇到星星两两的徒步人，大家相遇总是会面带微笑的打个招呼，也是互相激励的一种方式。这段路算是整个大朝台中最好走的了，在半路上的一个小坡顶可以望到台怀镇白塔寺的大白塔，还有丝丝升起的炊烟。一路上我们几个人拍点花、聊着天、看看牛羊就到了华北屋脊的牌坊处，坐下来大家把自己准备的食物分而食之。\n" +
                "\n" +
                " 这时，突然跑过来几只狐狸，没怎么见过大型野生动物的我们，也着实吓了一跳。它们倒是淡定很多，开始摇尾巴卖萌，盯着我手里的火腿肠。看这些狐狸的身材，也是被大朝台的游客们喂到了发福。\n" +
                "\n" +
                " 当我登顶北台的时候，虽是炎炎夏日，但吹在身上的风却是刺骨寒冷。看看周围的僧侣们，无一例外的裹着大棉袄，我心想那要是寒冬该怎么度过？想起了中国的一位旅游鼻祖，徐霞客。那一年是公元1633年，48岁的徐霞客登上了五台山，正值深秋。他在《徐霞客游记》中写到：“初六日风怒起，滴水成冰”。鲜花绿草估计徐霞客是看不到了，不过白雪皑皑对于他这个南方人，也算是一种体验。说话间，就听到滚滚的雷声，但北台顶没有下雨，这雨云飘在我们脚下。我们此时站在云雾之上，脚下是云雷滚滚，仿佛就像从天上看向人间。\n";
        data6.name="555555";
        data6.num_commu="0";
        data6.head="";
        data6.pic="http://139.199.37.80/Mount_WuTai/upload/6.jpg";

        Data data7 = new Data();
        data7.title ="菩萨顶游记";
        data7.abst="朝拜菩萨顶，最先映入眼帘的是一条长长宽宽的石砌台阶，共有108级。从下往上望去，宛若一座解脱之梯，据说“108”寓意着众生的六根接触六尘所产生的“百八烦恼”。";
        data7.name="天上掉馅饼";
        data7.num_read="5742";
        data7.num_commu="4523";
        data7.head="";
        data7.pic="http://139.199.37.80/Mount_WuTai/upload/7.jpg";



        listAdapter adapter = new listAdapter(this);
        List<Object> lists  = new ArrayList<>();
        lists.add(null);
        lists.add(data7);
        lists.add(data);
        lists.add(data1);
        lists.add(data2);
        lists.add(data3);
        lists.add(data4);
        lists.add(data5);
        lists.add(data6);

        adapter.lists = lists;
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    return ;
                }
                Intent intent = new Intent(MainActivity.this,ScrollingActivity.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });

        fbt = findViewById(R.id.fbt);
        fbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

}
