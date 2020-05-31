package com.app.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Syllabus extends AppCompatActivity {

    private Spinner semesters, subjects;
    private String[] semestersSpinner;
    private String[] firstSemester, secondSemester, thirdSemester, fourthSemester, fifthSemester, sixthSemester, seventhSemester, eightSemester;
    private ArrayAdapter semestersAdapter, subjectsAdapter;
    private TextView syllabusInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);
        semesters = (Spinner) findViewById(R.id.semesterSpinner);
        subjects = (Spinner) findViewById(R.id.subjectsSpinner);
        syllabusInfo=(TextView)findViewById(R.id.syllabusInfo) ;
        setUpSpinners();

    }

    private void setUpSpinners() {
        semestersSpinner = getResources().getStringArray(R.array.Semesters);
        firstSemester = getResources().getStringArray(R.array.FirstSemester);
        secondSemester = getResources().getStringArray(R.array.SecondSemester);
        thirdSemester = getResources().getStringArray(R.array.ThirdSemester);
        fourthSemester = getResources().getStringArray(R.array.FourthSemester);
        fifthSemester = getResources().getStringArray(R.array.FifthSemester);
        sixthSemester = getResources().getStringArray(R.array.SixthSemester);
        seventhSemester = getResources().getStringArray(R.array.SeventhSemester);
        eightSemester = getResources().getStringArray(R.array.EightSemester);
        semestersAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, semestersSpinner);
        semestersAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
        semesters.setAdapter(semestersAdapter);
        semesters.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, firstSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.chemSyllabus));
                                    syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.mathematicsISyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.ppsSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.chemLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.ppsLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.egdLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 1: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, secondSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.physicsSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.mathsIISyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.beeSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.comingSoon));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.physicsLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.beeLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.workshopLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 2: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, thirdSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.oopJSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.dataStructureSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.flatSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.deSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.mathsIIISyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.engEcoSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.oopjLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 7:{Spanned s= Html.fromHtml(getString(R.string.dataStructureLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 3: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, fourthSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.daaSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.discreteSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.databaseSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.coaSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.oBSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.evsSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.daaLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 7:{Spanned s= Html.fromHtml(getString(R.string.dmsLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 8:{Spanned s= Html.fromHtml(getString(R.string.coaLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 4: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, fifthSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.osSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.computerNetworkSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.compilerDesignSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.aISyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.ecommerceSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.advancedJavaSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.advancedCoaSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 7:{Spanned s= Html.fromHtml(getString(R.string.osLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 8:{Spanned s= Html.fromHtml(getString(R.string.computerNetworkLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 9:{Spanned s= Html.fromHtml(getString(R.string.compilerDesignLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 5: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, sixthSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.iwtSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.softwareTecnoSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.dataMiningSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.dataAnalysisSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.computerVisionSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.cloudComputingSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.computerGraphicsSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 7:{Spanned s= Html.fromHtml(getString(R.string.softComputingSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 8:{Spanned s= Html.fromHtml(getString(R.string.itwLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 9:{Spanned s= Html.fromHtml(getString(R.string.softwareEngineeringLabSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 10:{Spanned s= Html.fromHtml(getString(R.string.comingSoon));
                                        syllabusInfo.setText(s);
                                        break;}

                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 6: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, seventhSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.mobileComputingSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.realTimmeSystemsSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.wirelessSensorNetworkSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 3:{Spanned s= Html.fromHtml(getString(R.string.faultTolerantSystemSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 4:{Spanned s= Html.fromHtml(getString(R.string.softwareProjectManagementSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 5:{Spanned s= Html.fromHtml(getString(R.string.informationRetrievalSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 6:{Spanned s= Html.fromHtml(getString(R.string.entrepreneurshipDevSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                    case 7: {
                        subjectsAdapter = new ArrayAdapter(Syllabus.this, R.layout.spinner_textview, eightSemester);
                        subjectsAdapter.setDropDownViewResource(R.layout.spinner_dropdown_view);
                        subjects.setAdapter(subjectsAdapter);
                        subjects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                switch (position){
                                    case 0:{
                                        Spanned s= Html.fromHtml(getString(R.string.machineLearningSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 1:{
                                        Spanned s= Html.fromHtml(getString(R.string.embeddedSystemSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                    case 2:{Spanned s= Html.fromHtml(getString(R.string.intrusionDetectionSyllabus));
                                        syllabusInfo.setText(s);
                                        break;}
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
