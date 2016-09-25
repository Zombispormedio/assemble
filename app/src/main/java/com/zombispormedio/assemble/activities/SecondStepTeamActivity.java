package com.zombispormedio.assemble.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.items.BottomSheetMenuItem;
import com.squareup.picasso.Picasso;
import com.zombispormedio.assemble.R;
import com.zombispormedio.assemble.adapters.lists.ParticipantsListAdapter;
import com.zombispormedio.assemble.controllers.SecondStepTeamController;
import com.zombispormedio.assemble.models.FriendProfile;
import com.zombispormedio.assemble.utils.AndroidUtils;
import com.zombispormedio.assemble.utils.ExternalNavigationManager;
import com.zombispormedio.assemble.utils.ImageUtils;
import com.zombispormedio.assemble.utils.NavigationManager;
import com.zombispormedio.assemble.views.ISecondStepTeamView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class SecondStepTeamActivity extends BaseActivity implements ISecondStepTeamView {

    private SecondStepTeamController ctrl;

    private ExternalNavigationManager externalNavigationManager;

    @BindView(R.id.image_view)
    ImageView imageView;

    @BindView(R.id.participants_label)
    TextView participantsLabel;

    @BindView(R.id.name_input)
    EditText nameInput;

    @BindView(R.id.participants_list)
    RecyclerView participantsList;

    private ParticipantsListAdapter participantsListAdapter;

    private BottomSheetDialog imageUploaderBottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_step_team);
        setupToolbar();
        bindActivity(this);
        setSubtitle(R.string.add_name);
        Bundle extra=getIntent().getExtras();

        ctrl=new SecondStepTeamController(this, extra.getIntArray(NavigationManager.ARGS+0));
        externalNavigationManager = new ExternalNavigationManager(this);

        setupIcon();

        setupParticipants();

        setupImageUploaderBottomSheet();

        ctrl.onCreate();
    }

    private void setupParticipants() {
        AndroidUtils.createListConfiguration(this, participantsList)
                .grid(true)
                .span(4)
                .itemAnimation(true)
                .configure();

        participantsListAdapter= new ParticipantsListAdapter();
        participantsList.setAdapter(participantsListAdapter);
    }

    private void setupIcon() {
        new ImageUtils.ImageBuilder(this, imageView)
                .circle(true)
                .drawableID(R.drawable.profile_image_square)
                .build();
    }


    @Override
    public void setParticipantsTitle(int number, int total) {
        String participants=String.format(getString(R.string.participants_number), number,total);
        participantsLabel.setText(participants);
    }

    @Override
    public String getName() {
        return nameInput.getText().toString();
    }

    @Override
    public void bindParticipants(ArrayList<FriendProfile> data) {
        participantsListAdapter.setData(data);
    }

    private void setupImageUploaderBottomSheet() {
        imageUploaderBottomSheet = new BottomSheetBuilder(this, R.style.AppTheme_BottomSheetDialog)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setBackground(R.color.colorWhite)
                .setMenu(R.menu.menu_bottom_sheet)
                .setItemClickListener(new BottomSheetItemClickListener() {
                    @Override
                    public void onBottomSheetItemClick(BottomSheetMenuItem item) {
                        switch (item.getId()) {
                            case R.id.gallery:
                                externalNavigationManager.dispatchGalleryToSelectImage(R.string.select_picture);
                                break;
                            case R.id.camera:
                                externalNavigationManager.dispatchTakePicture();
                                break;
                        }
                    }
                })
                .createDialog();
    }

    @OnClick(R.id.image_view)
    public void onClickImage(View view){
        imageUploaderBottomSheet.show();
    }
}
