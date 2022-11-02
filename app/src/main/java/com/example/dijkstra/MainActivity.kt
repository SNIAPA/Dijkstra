package com.example.dijkstra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    lateinit var startingNumberPicker: NumberPicker;
    lateinit var endingNumberPicker: NumberPicker;
    lateinit var pathfindButton: Button
    lateinit var ansTextView: TextView

    lateinit var graph: Graph

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startingNumberPicker = findViewById(R.id.startingNumberPicker)
        endingNumberPicker = findViewById(R.id.endingNumberPicker)
        pathfindButton = findViewById(R.id.pathfindButton)
        ansTextView = findViewById(R.id.ansTextView)


        pathfindButton.setOnClickListener {
            val dijkstra = Dijkstra(graph)
            val path = dijkstra.pathfind(startingNumberPicker.value,endingNumberPicker.value)

            ansTextView.text = path?.toString() ?: "NO PATH"
            Log.d("path", path?.toString() ?: "NO PATH")
        }


        graph = Graph(10,25,10)

        startingNumberPicker.minValue = 0;
        startingNumberPicker.maxValue  = 9;

        endingNumberPicker.minValue = 0;
        endingNumberPicker.maxValue  = 9;




    }
}