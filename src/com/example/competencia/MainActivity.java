package com.example.competencia;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	String v[]=new String[5000];
	int indice=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String estado=Environment.getExternalStorageState();
		if(!estado.equals(Environment.MEDIA_MOUNTED))
		{
			Toast.makeText(this, "no hay memoria SD", 1).show();
			finish();
		}
		File dir=Environment.getExternalStorageDirectory();
		File arch=new File(dir.getAbsolutePath()+File.separator+"ventas.txt");
		try
		{
			Scanner lee=new Scanner(new FileReader(arch));
			String linea="";
			while(lee.hasNext())
			{
				linea=lee.nextLine();
				v[indice++]=linea;
			}
				
			}
			catch(IOException e)
			{
				e.getMessage();
			}
		
	}
	public void buscar(View vista)
	{
		EditText pCod =(EditText) findViewById(R.id.editText1);
		EditText pCant =(EditText) findViewById(R.id.editText3);
		EditText pMonto =(EditText) findViewById(R.id.editText4);
		EditText pReg =(EditText) findViewById(R.id.editText2);
		String qCod=pCod.getText().toString();
		double tc=0, tv=0;
		int nc=0;
		for(int i=0;i<v.length;i++)
		{
			if(v[i].contains(qCod))
			{
				nc++;
				String datos[]=v[i].split(";");
				double cant =Double.parseDouble(datos[3]);
				double pv =Double.parseDouble(datos[4]);
				tc+=cant;
				tv=tv+cant*pv;
			}
		}
		String cantidad="",monto="";
		cantidad=cantidad+tc;
		monto=monto+tv;
		pReg.setText((nc+""));
		pMonto.setText(monto);
		pCant.setText(cantidad);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
