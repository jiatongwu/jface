package cn.xvkang.compoent;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class AddComposite extends Composite {
	private Text text;
	private Text text_1;
	private Button saveBtn;
	private Button cancelBtn;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public AddComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FormLayout());

		Label label = new Label(this, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.right = new FormAttachment(0, 135);
		fd_label.top = new FormAttachment(0, 103);
		fd_label.left = new FormAttachment(0, 64);
		label.setLayoutData(fd_label);
		label.setText("姓名");

		Label label_1 = new Label(this, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.right = new FormAttachment(label, 0, SWT.RIGHT);
		fd_label_1.top = new FormAttachment(label, 63);
		fd_label_1.left = new FormAttachment(0, 64);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("年龄");

		text = new Text(this, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(0, 100);
		fd_text.left = new FormAttachment(label, 6);
		fd_text.right = new FormAttachment(0, 292);
		text.setLayoutData(fd_text);

		text_1 = new Text(this, SWT.BORDER);
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(label_1, -3, SWT.TOP);
		fd_text_1.right = new FormAttachment(text, 0, SWT.RIGHT);
		fd_text_1.left = new FormAttachment(label_1, 6);
		text_1.setLayoutData(fd_text_1);

		saveBtn = new Button(this, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.bottom = new FormAttachment(text_1, 195, SWT.BOTTOM);
		fd_button.left = new FormAttachment(0, 141);
		fd_button.bottom = new FormAttachment(100, -101);
		saveBtn.setLayoutData(fd_button);
		saveBtn.setText("保存");

		cancelBtn = new Button(this, SWT.NONE);
		fd_button.right = new FormAttachment(cancelBtn, -28);
		FormData fd_cancel_button = new FormData();
		fd_cancel_button.bottom = new FormAttachment(100, -101);
		fd_cancel_button.right = new FormAttachment(100, -233);
		fd_cancel_button.left = new FormAttachment(0, 311);
		cancelBtn.setLayoutData(fd_cancel_button);
		cancelBtn.setText("取消");

	}

	@Override
	protected void checkSubclass() {

	}

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Text getText_1() {
		return text_1;
	}

	public void setText_1(Text text_1) {
		this.text_1 = text_1;
	}

	public Button getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(Button saveBtn) {
		this.saveBtn = saveBtn;
	}

	public Button getCancelBtn() {
		return cancelBtn;
	}

	public void setCancelBtn(Button cancelBtn) {
		this.cancelBtn = cancelBtn;
	}
	

}
