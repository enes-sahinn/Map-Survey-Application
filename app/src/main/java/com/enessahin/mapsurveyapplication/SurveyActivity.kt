package com.enessahin.mapsurveyapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class SurveyActivity : AppCompatActivity() {
    var resultString =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey)

        // access the items of the list
        val languages = resources.getStringArray(R.array.Languages)
        // access the spinner
        val spinner = findViewById(R.id.spinner) as Spinner
        var selected_lecture = ""
        if (spinner != null) {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, languages)
            spinner.adapter = adapter
        }

        if (spinner != null) {

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    selected_lecture = languages[position]
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }
        }

        var radioButton4 = findViewById(R.id.radioButton4) as RadioButton
        radioButton4.setChecked(true)

        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            var textInputEditText = findViewById(R.id.textInputEditText) as EditText
            var editText = textInputEditText.text.toString()

            var cb1 = findViewById(R.id.checkBox) as CheckBox
            var cb2 = findViewById(R.id.checkBox2) as CheckBox
            var cb3 = findViewById(R.id.checkBox3) as CheckBox
            var result = ""
            if (cb1.isChecked) {
                result += " " + cb1.text.toString()
            }
            if (cb2.isChecked) {
                result += " " + cb2.text.toString()
            }
            if (cb3.isChecked) {
                result += " " + cb3.text.toString()
            }
            var checkBoxResult = result


            var radioGroup = findViewById(R.id.radioGroup) as RadioGroup

            // Getting the checked radio button id from the radio group
            val selectedOption: Int = radioGroup!!.checkedRadioButtonId
            // Assigning id of the checked radio button
            var radioButton = findViewById(selectedOption) as RadioButton
            var radioButtonText = radioButton.text


            Toast.makeText(this@SurveyActivity,
                editText + " " +  selected_lecture + " " +  radioButtonText + " " +  checkBoxResult,
                Toast.LENGTH_SHORT).show()

            resultString =  editText + ", " +  selected_lecture + ", " +  radioButtonText + "," +  checkBoxResult
            callActivity()
        }
    }

    private fun callActivity() {
        var intent = Intent(applicationContext, ResultActivity::class.java).also {
            it.putExtra("data", resultString)
            startActivity(it)
        }
    }
}