package masterzht.top.appranking.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;

import masterzht.top.appranking.R;
import masterzht.top.appranking.base.BaseActivity;
import masterzht.top.appranking.ui.fragment.first.RankDetailFragment;

public class ExtendsActivity extends BaseActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_extends);

        Intent mIntent = this.getIntent();//直接getIntent
        Bundle mBundle = mIntent.getExtras();  //两句可以合并成一句
        String id = mBundle.getString("id");
        String toolbar_title=mBundle.getString("toolbar_title");


        // Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        if (findFragment(RankDetailFragment.class) == null) {
            loadRootFragment(R.id.fl_container, RankDetailFragment.newInstance(id));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
