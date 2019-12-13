package com.example.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HeroAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<Hero> heroes = new ArrayList<>();

    public void setHeroes(ArrayList<Hero> hero) {
        this.heroes = hero;
    }

    public HeroAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return heroes.size();
    }

    @Override
    public Object getItem(int i) {
        return heroes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.item_xml,viewGroup,false);
        }

        ViewHolder viewHolder = new ViewHolder(itemView);
        Hero hero = (Hero) getItem(i);
        viewHolder.bind(hero);

        return itemView;
    }

    private class ViewHolder{

        private TextView txtName,txtDesc;
        private ImageView imgPhoto;

        ViewHolder(View view){
            txtName = view.findViewById(R.id.txt_name);
            txtDesc = view.findViewById(R.id.txt_desc);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Hero hero){
            txtName.setText(hero.getName());
            txtDesc.setText(hero.getDescription());
            imgPhoto.setImageResource(hero.getPhoto());
        }

    }


}
