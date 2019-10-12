package top.seiei.saasaps.bo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeBeanForProductionLineRight {
	private String title;
	private boolean expand = false;
	private List<TreeBeanForProductionLineRight> children;
	private Integer id;
	private boolean checked = false;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isExpand() {
		return expand;
	}
	public void setExpand(boolean expand) {
		this.expand = expand;
	}
	public List<TreeBeanForProductionLineRight> getChildren() {
		return children;
	}
	public void setChildren(List<TreeBeanForProductionLineRight> children) {
		this.children = children;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
