package com.example.wutai.wutaimoutain.Stroy;

import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;

public class Stroy_Activity extends AppCompatActivity {
    String temp1,temp2,temp3,temp4;
    ImageView temp1_pic,temp2_pic,temp3_pic;
    TextView text_set1,text_set2,text_set3,text_set4;
    private Toolbar toolbar ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroy_);
        toolbar = (Toolbar)findViewById(R.id.stroy_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        temp1_pic = (ImageView)findViewById(R.id.img_set1);
        temp2_pic = (ImageView)findViewById(R.id.img_set2);
        temp3_pic = (ImageView)findViewById(R.id.img_set3);
        text_set1 = (TextView)findViewById(R.id.text_set1);
        text_set2 =(TextView)findViewById(R.id.text_set2);
        text_set3=(TextView)findViewById(R.id.text_set3);
        text_set4=(TextView)findViewById(R.id.text_set4);
        temp1 = "   五台山位于山西省东北部，忻州市五台县和繁峙县之间" +
                "，距省会太原是240千米，与浙江普陀山，安徽九华山，四川峨眉山共称中国佛教四大名山。" +
                "与尼泊尔蓝毗尼花园，印度鹿野苑，菩提伽耶，拘尸那迦并称为世界五大佛教圣地。" +
                "在中国素有“地下文物看陕西，地上文物看山西”和“三千年历史看陕西，五千年历史看山西”" +
                "的说法，厚重的黄土地奠定了这里民风的质朴，造就了这里风物千姿.";
        temp2 = "   五台山属太行山脉的北端，与忻州市繁峙县，代县，原平市，定襄县，五台县都有接壤，周五百余里。中心地区台怀镇，距五台县城78千米，繁峙县砂河镇48千米，心中是150千米，山西省会太原市240千米。五台山地质古老，地貌奇特，是著名的国家地质公园，由五个突兀而出的台顶和其他一系列群山峻峰组成。\n" +
                "    五台山最低处海拔仅624米，最高处海拔达3061米，为华北最高峰，有“华北屋脊”之称。层峦叠嶂，峰岭交错，苍茫壮丽。神奇的自然地貌除了为这里造就了许多独特的景观外，还蕴含着多种有经济价值的矿石，其中又以黑曜石最为著名，有重大的经济价值。\n" +
                "    五台山拥有独特而完整的地球早期地质构造，地层剖面，古生物化石遗迹，新生代夷平面及冰缘地貌，完整记录了地球新太古代晚期古元古代地质演化历史，具有世界性地质构造和年代地层划分意义和对比价值，是全球地质科学界研究地球早期演化及早期板块碰撞造山记录过程的最佳记录，是开展全球性地壳演化，古环境，生物演化对比研究的典型例证。因此，五台山当之无愧的被誉为中国地质博物馆。\n";
        temp3 ="    五台山全年平均气温为零下4℃，七至八月最热，分别为9.5℃和8.5℃。一月份最冷，平均气温零下18.8℃。台怀地区呈一个狭长的小盆地。夏季湿度大，常有雨水，秋冬季节气候寒冷，要多注意保暖，春夏季节要注意随时携带雨具" +
                "。山中气候寒冷，台顶终年有冰，尤其突出的是盛夏天气凉爽，故又称清凉山，为避暑圣地。\n";
        temp4 ="    五台山位于太行山脉的北段，地处黄土高原，地旱树稀，整个都是土黄色的世界，而五台山就像是镶嵌在黄土高原之上的一块翡翠，是晋善晋美的点睛之笔。这里春天花开遍野，夏天清凉碧绿，秋天璀璨金黄，冬天银装素裹。一年四季都是婀娜多姿，气象万千。五台山风光如画，或平缓或陡峭的峰崖，高峻挺拔的古松劲柏。飘渺弥漫的云山雾海，以及烟波浩渺中金碧辉煌的殿宇楼台，构成一派锦绣天地。\n" +
                "五台山不单自然景观呈现旷世的静美，更叫人叹服的是精彩纷呈的人文景观，幽深古朴的伽蓝古刹比比皆是，随时置身于“晨钟暮鼓，菩提梵唱”的佛教氛围。五台山有多如繁星的佛像与雕塑，可以称得上是一个工艺与美术的大宝藏，也吸引着艺术界的同仁们流连忘返。\n" +
                "    无论从旖旎的自然风光、珍贵的宗教建筑、灿若星辰的工艺美术上来说，" +
                "还是从古朴厚重的人文景观以及浓郁的佛教氛围上讲，无不体现出五台山传承中" +
                "华民族传统文化与佛教文化的重要性与不可替代性。尤其是近年来五台山作为佛教" +
                "圣地对于山区规划和景观设计方面取得重大成就。也为今后佛教的发展，佛教圣地作为旅游资源的规划提供了新的思路。";
        Glide.with(Stroy_Activity.this).load(R.drawable.stroy_show1).into(temp1_pic);
        Glide.with(Stroy_Activity.this).load(R.drawable.stroy_show2).into(temp2_pic);
        Glide.with(Stroy_Activity.this).load(R.drawable.stroy_show3).into(temp3_pic);
        text_set1.setText(temp1);
        text_set2.setText(temp2);
        text_set3.setText(temp3);
        text_set4.setText(temp4);
    }
}
