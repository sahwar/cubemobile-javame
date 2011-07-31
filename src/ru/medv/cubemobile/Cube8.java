package ru.medv.cubemobile;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author DimOn
 */
public class Cube8
{
	Point3 cube[];
	Cube8GranMIDlet gran[];
	
	Cube8()
	{
		cube=new Point3[8];		
		cube[0]=new Point3(+1.0,+1.0,-1.0); //� ��. ��
		cube[1]=new Point3(-1.0,+1.0,-1.0); //� ��. ��
		cube[2]=new Point3(-1.0,-1.0,-1.0); //� ��. ��
		cube[3]=new Point3(+1.0,-1.0,-1.0); //� ��. ��
		cube[4]=new Point3(+1.0,+1.0,+1.0); //� ��. ��
		cube[5]=new Point3(-1.0,+1.0,+1.0); //� ��. ��
		cube[6]=new Point3(-1.0,-1.0,+1.0); //� ��. ��
		cube[7]=new Point3(+1.0,-1.0,+1.0); //� ��. ��
		gran=new Cube8GranMIDlet[6];                
		gran[0]=new Cube8GranMIDlet(0,1,2,3, Color.BLUE);   //� ��.
		gran[1]=new Cube8GranMIDlet(4,5,1,0, Color.CYAN);   //up ��.
		gran[2]=new Cube8GranMIDlet(5,6,2,1, Color.GREEN);  //lt ��.
		gran[3]=new Cube8GranMIDlet(6,7,3,2, Color.MAGENTA);//dn ��.
		gran[4]=new Cube8GranMIDlet(7,4,0,3, Color.RED);    //rt ��.
		gran[5]=new Cube8GranMIDlet(7,6,5,4, Color.YELLOW); //� ��.
	}
	
	void draw( Graphics g, PhisicalSys  ps)
	{
		for(int i=0;i<6;i++)
			if(isGranVisible(i))
				drawGran(g, ps, i);
	}
	
	void rotX(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x;
			np.y = cube[i].y * C - cube[i].z * S;
			np.z = cube[i].y * S + cube[i].z * C;			
			cube[i] = np;
		}
	}
	void rotY(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x * C + cube[i].z * S;
			np.y = cube[i].y;
			np.z = -cube[i].x * S + cube[i].z * C;			
			cube[i] = np;
		}
	}
	void rotZ(double g)
	{
		double C = Math.cos(g); 
		double S = Math.sin(g);
		for(int i=0;i<8;i++)
		{
			Point3 np = new Point3(); 
			np.x = cube[i].x * C - cube[i].y * S;
			np.y = cube[i].x * S + cube[i].y * C;
			np.z = cube[i].z;			
			cube[i] = np;
			/*
			Point3 pcopy = new Point3();
			pcopy = cube[i];
			cube[i].x = pcopy.x * C - pcopy.y * S;	
			cube[i].y = pcopy.x * S + pcopy.y * C;
			cube[i].z = pcopy.z;
			*/
		}
	}
	
	void drawGran(Graphics g, PhisicalSys ps, int gri)
	{	
		Cube8GranMIDlet cg = gran[gri];
		
                /*
		Polygon pl = new Polygon();
		pl.addPoint(
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[0] ] ).y
				);
		pl.addPoint(
				ps.fromPoint3( cube[ cg.indexVert[1] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[1] ] ).y
				);
		pl.addPoint(
				ps.fromPoint3( cube[ cg.indexVert[2] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[2] ] ).y
				);
		pl.addPoint(
				ps.fromPoint3( cube[ cg.indexVert[3] ] ).x,
				ps.fromPoint3( cube[ cg.indexVert[3] ] ).y
				);		
	
		g.setColor( ColorMix.Mul( cg.color, getCosPhiGran(gri) ) );
		g.fillPolygon(pl);
                */
                
                g.setColor( Color.mul( cg.color, getCosPhiGran(gri) ).getRGB() );
                g.fillTriangle(
                        ps.fromPoint3( cube[ cg.indexVert[0] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[0] ] ).y,
                        
                        ps.fromPoint3( cube[ cg.indexVert[1] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[1] ] ).y,
                        
                        ps.fromPoint3( cube[ cg.indexVert[2] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[2] ] ).y
                        );
                
                g.fillTriangle(
                        ps.fromPoint3( cube[ cg.indexVert[2] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[2] ] ).y,
                        
                        ps.fromPoint3( cube[ cg.indexVert[3] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[3] ] ).y,
                        
                        ps.fromPoint3( cube[ cg.indexVert[0] ] ).x,
			ps.fromPoint3( cube[ cg.indexVert[0] ] ).y
                        );
	}
	
	Point3 getNormalGran(int gri)
	{
		Cube8GranMIDlet cg = gran[gri];
		
		double X1=cube[cg.indexVert[0]].x;
		double Y1=cube[cg.indexVert[0]].y;
		double Z1=cube[cg.indexVert[0]].z;
		double X2=cube[cg.indexVert[1]].x;
		double Y2=cube[cg.indexVert[1]].y;
		double Z2=cube[cg.indexVert[1]].z;
		double X3=cube[cg.indexVert[2]].x;
		double Y3=cube[cg.indexVert[2]].y;
		double Z3=cube[cg.indexVert[2]].z;		
		
		double xv = (Y1-Y2)*(Z1-Z3) - (Z1-Z2)*(Y1-Y3);
		double yv = (Z1-Z2)*(X1-X3) - (X1-X2)*(Z1-Z3);
		double zv = (X1-X2)*(Y1-Y3) - (Y1-Y2)*(X1-X3);
				
		return new Point3( xv, yv, zv );
	}
	
	/** ������� (������������^�����������������)
	 * @param gri - ������ �����
	 * @return ������� ���� ����� �������� � ����� � �������� �� ��� ���� 
	 */
	double getCosPhiGran(int gri)
	{
		Point3 p3 = getNormalGran(gri);	
		Point3 pVis = new Point3(0,0,1);
		return p3.getScalar(pVis) / p3.getVectorLenght() / pVis.getVectorLenght();
	}
	
	boolean isGranVisible( int gri )
	{		
		return ( getCosPhiGran(gri) > 0 );		
	}
}


/** ����� ���� - ������� ������ � ���� �����. */
class Cube8GranMIDlet
{
	int indexVert[];
	Color color;
	public Cube8GranMIDlet( int v1, int v2, int v3, int v4, Color c)
	{
		indexVert = new int[4];
		indexVert[0] = v1;
		indexVert[1] = v2;
		indexVert[2] = v3;
		indexVert[3] = v4;
		color = c;
	}	
}
