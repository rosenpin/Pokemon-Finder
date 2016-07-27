package pokemonfinder.com.pokemonfinder.Views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pokemonfinder.com.pokemonfinder.Pokemon;
import pokemonfinder.com.pokemonfinder.R;

/**
 * Created by tomer AKA rosenpin on 7/27/16.
 */
public class PokemonAdapter extends BaseAdapter {

    ArrayList<Pokemon> pokemon;
    Context context;

    public PokemonAdapter(Context context, ArrayList<Pokemon> pokemon) {
        this.context = context;
        this.pokemon = pokemon;
    }

    @Override
    public int getCount() {
        return pokemon.size();
    }

    @Override
    public Object getItem(int position) {
        return pokemon.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            final LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.pokemon_list_item, parent, false);
        }

        int distance = pokemon.get(position).getDistance(context);
        ((ImageView) view.findViewById(R.id.image)).setImageResource(pokemon.get(position).getResourceID());
        ((ImageView) view.findViewById(R.id.footsteps)).setImageResource(distance < 333 ? R.drawable.steps_1 : (distance < 666 ? R.drawable.steps_2 : R.drawable.steps_3));
        ((TextView) view.findViewById(R.id.name)).setText(pokemon.get(position).getName());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("geo:0,0?q=" + pokemon.get(position).getLat() + "," + pokemon.get(position).getLng() + "(" + pokemon.get(position).getName() + ")"));
                context.startActivity(intent);
            }
        });
        ((TextView) view.findViewById(R.id.distance)).setText(String.valueOf(distance + " meters away"));
        return view;
    }
}

