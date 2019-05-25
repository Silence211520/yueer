package yueer.utils;

/**
 * 分页工具类
 * 
 * @author Silence
 *
 *         2018年12月19日 上午7:18:16
 */
public class PageUtils {
	// 每页显示的记录数目
	private int rows;
	// 第几页,页数从1开始
	private int page;
	// 分页中第一条记录的索引
	private int startIndex;

	public PageUtils() {
		this.rows = 10;
		this.page = 1;
	}

	public PageUtils(int page, int rows) {
		this.page = page;
		this.rows = rows;
	}
   /**
    * 获取查询的起始位置
    * @return
    */
	public int getIndex() {
		int pos = 0;
		// limit 语句中位置从0开始
		pos = (this.page - 1) > 0 ? (this.page - 1) * this.rows: 0;
		return pos;
	}

	public int getRows() {
		return rows;
	}

	public int getPage() {
		return page;
	}

}
