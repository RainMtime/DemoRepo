package rainmtime.com.demorepo.movies.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rainmtime.com.demorepo.R;
import rainmtime.com.demorepo.movies.data.Subject;

/**
 * Created by chunyu on 2018/2/12 上午10:57.
 * Email: 746431278@qq.com
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    @BindView(R.id.ll_item)
    LinearLayout llItem;
    @BindView(R.id.img_movie)
    ImageView imgMovie;
    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.tv_movie_director)
    TextView tvMovieDirector;
    @BindView(R.id.tv_movie_cast)
    TextView tvMovieCast;
    @BindView(R.id.tv_movie_average)
    TextView tvMovieAverage;

    public MovieViewHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        ButterKnife.bind(this, itemView);
    }

    public void renderView(Subject subject) {
//        if (subject != null) {
//
//            if (subject.getImages() != null) {
//                Glide.with(mContext).load(subject.getImages().getMedium()).into(imgMovie);
//            }
//            tvMovieTitle.setText(subject.getTitle());
//
//            String directors = "";
//            if (subject.getDirectors() != null) {
//                for (int i = 0; i < subject.getDirectors().size(); i++) {
//                    Director director = subject.getDirectors().get(i);
//                    if (i == subject.getDirectors().size() - 1) {
//                        directors = directors + director.getName();
//                    } else {
//                        directors = directors + director.getName() + "、";
//                    }
//                }
//            } else {
//                directors = ResUtils.getString(R.string.unknown);
//            }
//
//            tvMovieDirector.setText(ResUtils.getString(R.string.director, directors));
//
//            //设置主演
//            String casts = "";
//            if (subject.getCasts() != null) {
//                for (int i = 0; i < subject.getCasts().size(); i++) {
//                    Cast cats = subject.getCasts().get(i);
//                    if (i == subject.getCasts().size() - 1) {
//                        casts = casts + cats.getName();
//                    } else {
//                        casts = casts + cats.getName() + "、";
//                    }
//                }
//            } else {
//                casts = ResUtils.getString(R.string.unknown);
//            }
//            tvMovieCast.setText(String.format(ResUtils.getString(R.string.cast), casts));
//            //设置分数
//            tvMovieAverage.setText(String.format(ResUtils.getString(R.string.average), "" + subject.getRating().getAverage()));
//            //点击回调
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//                }
//            });
//        }


    }


}
