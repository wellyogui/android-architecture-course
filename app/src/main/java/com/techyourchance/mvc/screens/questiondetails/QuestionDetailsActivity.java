package com.techyourchance.mvc.screens.questiondetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.techyourchance.mvc.networking.QuestionDetailsResponseSchema;
import com.techyourchance.mvc.networking.QuestionSchema;
import com.techyourchance.mvc.networking.StackoverflowApi;
import com.techyourchance.mvc.screens.common.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionDetailsActivity extends BaseActivity {

    public static final String EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID";

    private StackoverflowApi stackoverflowApi;

    public static void start(Context context, String questionId) {
        Intent intent = new Intent(context, QuestionDetailsActivity.class);
        intent.putExtra(EXTRA_QUESTION_ID, questionId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stackoverflowApi = getCompositionRoot().getStackoverflowApi();
    }

    private void fetchDetails(String questionId){
        stackoverflowApi.fetchQuestionDetails(questionId)
                .enqueue(new Callback<QuestionDetailsResponseSchema>() {
            @Override
            public void onResponse(Call<QuestionDetailsResponseSchema> call, Response<QuestionDetailsResponseSchema> response) {
                if (response.isSuccessful()) {
                    bindQuestions(response.body().getQuestion());
                } else {
                    networkCallFailed();
                }
            }

            @Override
            public void onFailure(Call<QuestionDetailsResponseSchema> call, Throwable t) {
                networkCallFailed();
            }
        } );
    }

    private void networkCallFailed() {

    }

    private void bindQuestions(QuestionSchema question) {

    }
}
