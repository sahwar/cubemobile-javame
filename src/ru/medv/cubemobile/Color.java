package ru.medv.cubemobile;

/**
 * @author DimOn
 */
public class Color
{
	final static Color BLUE = new Color(0, 0, 255);
	final static Color CYAN = new Color(0, 255, 255);
	final static Color GREEN = new Color(0, 255, 0);
	final static Color MAGENTA = new Color(255, 0, 255);
	final static Color RED = new Color(255, 0, 0);
	final static Color YELLOW = new Color(255, 255, 0);

	int r;
	int g;
	int b;

	public Color()
	{
	}

	public Color(int r, int g, int b)
	{
		this.r = r;
		this.g = g;
		this.b = b;
	}

	public int getRGB()
	{
		int bi = b % 256;
		int gi = (g % 256) << 8;
		int ri = (r % 256) << 16;
		return bi + gi + ri;
	}
	
	/**
	 * @param c
	 *            ���� ������������
	 * @param mul
	 *            ��������� (����� �������) [0..1]
	 * @return ���������� (�����������) ����
	 */
	static Color mul(Color c, double mul)
	{
		return new Color((int) (c.r * mul), (int) (c.g * mul), (int) (c.b * mul));
	}
}
