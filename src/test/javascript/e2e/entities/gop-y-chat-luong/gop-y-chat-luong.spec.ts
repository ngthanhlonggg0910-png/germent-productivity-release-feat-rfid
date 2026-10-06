import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GopYChatLuongComponentsPage, GopYChatLuongDeleteDialog, GopYChatLuongUpdatePage } from './gop-y-chat-luong.page-object';

const expect = chai.expect;

describe('GopYChatLuong e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let gopYChatLuongComponentsPage: GopYChatLuongComponentsPage;
  let gopYChatLuongUpdatePage: GopYChatLuongUpdatePage;
  let gopYChatLuongDeleteDialog: GopYChatLuongDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GopYChatLuongs', async () => {
    await navBarPage.goToEntity('gop-y-chat-luong');
    gopYChatLuongComponentsPage = new GopYChatLuongComponentsPage();
    await browser.wait(ec.visibilityOf(gopYChatLuongComponentsPage.title), 5000);
    expect(await gopYChatLuongComponentsPage.getTitle()).to.eq('ctApp.gopYChatLuong.home.title');
  });

  it('should load create GopYChatLuong page', async () => {
    await gopYChatLuongComponentsPage.clickOnCreateButton();
    gopYChatLuongUpdatePage = new GopYChatLuongUpdatePage();
    expect(await gopYChatLuongUpdatePage.getPageTitle()).to.eq('ctApp.gopYChatLuong.home.createOrEditLabel');
    await gopYChatLuongUpdatePage.cancel();
  });

  it('should create and save GopYChatLuongs', async () => {
    const nbButtonsBeforeCreate = await gopYChatLuongComponentsPage.countDeleteButtons();

    await gopYChatLuongComponentsPage.clickOnCreateButton();
    await promise.all([
      gopYChatLuongUpdatePage.setCusIdInput('5'),
      gopYChatLuongUpdatePage.setCusNameInput('cusName'),
      gopYChatLuongUpdatePage.setChuyenIdInput('5'),
      gopYChatLuongUpdatePage.setChuyenNameInput('chuyenName'),
      gopYChatLuongUpdatePage.setMaHangInput('maHang'),
      gopYChatLuongUpdatePage.setPoInput('po'),
      gopYChatLuongUpdatePage.setPoIdInput('5'),
      gopYChatLuongUpdatePage.setColorInput('color'),
      gopYChatLuongUpdatePage.setSizeInput('size'),
      gopYChatLuongUpdatePage.setLanBanHanhInput('5'),
      gopYChatLuongUpdatePage.setNgayBaoCaoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gopYChatLuongUpdatePage.setNguoiTaoInput('nguoiTao'),
      gopYChatLuongUpdatePage.setUserIdInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gopYChatLuongUpdatePage.setNgayTaoInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      gopYChatLuongUpdatePage.setTenQuanLyXuongInput('tenQuanLyXuong'),
      gopYChatLuongUpdatePage.setTenKiThuatInput('tenKiThuat'),
      gopYChatLuongUpdatePage.setTenQanLyCatInput('tenQanLyCat'),
      gopYChatLuongUpdatePage.setTenQAInput('tenQA')
    ]);
    expect(await gopYChatLuongUpdatePage.getCusIdInput()).to.eq('5', 'Expected cusId value to be equals to 5');
    expect(await gopYChatLuongUpdatePage.getCusNameInput()).to.eq('cusName', 'Expected CusName value to be equals to cusName');
    expect(await gopYChatLuongUpdatePage.getChuyenIdInput()).to.eq('5', 'Expected chuyenId value to be equals to 5');
    expect(await gopYChatLuongUpdatePage.getChuyenNameInput()).to.eq('chuyenName', 'Expected ChuyenName value to be equals to chuyenName');
    expect(await gopYChatLuongUpdatePage.getMaHangInput()).to.eq('maHang', 'Expected MaHang value to be equals to maHang');
    expect(await gopYChatLuongUpdatePage.getPoInput()).to.eq('po', 'Expected Po value to be equals to po');
    expect(await gopYChatLuongUpdatePage.getPoIdInput()).to.eq('5', 'Expected poId value to be equals to 5');
    expect(await gopYChatLuongUpdatePage.getColorInput()).to.eq('color', 'Expected Color value to be equals to color');
    expect(await gopYChatLuongUpdatePage.getSizeInput()).to.eq('size', 'Expected Size value to be equals to size');
    const selectedFirstCut = gopYChatLuongUpdatePage.getFirstCutInput();
    if (await selectedFirstCut.isSelected()) {
      await gopYChatLuongUpdatePage.getFirstCutInput().click();
      expect(await gopYChatLuongUpdatePage.getFirstCutInput().isSelected(), 'Expected firstCut not to be selected').to.be.false;
    } else {
      await gopYChatLuongUpdatePage.getFirstCutInput().click();
      expect(await gopYChatLuongUpdatePage.getFirstCutInput().isSelected(), 'Expected firstCut to be selected').to.be.true;
    }
    const selectedInline = gopYChatLuongUpdatePage.getInlineInput();
    if (await selectedInline.isSelected()) {
      await gopYChatLuongUpdatePage.getInlineInput().click();
      expect(await gopYChatLuongUpdatePage.getInlineInput().isSelected(), 'Expected inline not to be selected').to.be.false;
    } else {
      await gopYChatLuongUpdatePage.getInlineInput().click();
      expect(await gopYChatLuongUpdatePage.getInlineInput().isSelected(), 'Expected inline to be selected').to.be.true;
    }
    expect(await gopYChatLuongUpdatePage.getLanBanHanhInput()).to.eq('5', 'Expected lanBanHanh value to be equals to 5');
    expect(await gopYChatLuongUpdatePage.getNgayBaoCaoInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayBaoCao value to be equals to 2000-12-31'
    );
    expect(await gopYChatLuongUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await gopYChatLuongUpdatePage.getUserIdInput()).to.contain(
      '2001-01-01T02:30',
      'Expected userId value to be equals to 2000-12-31'
    );
    expect(await gopYChatLuongUpdatePage.getNgayTaoInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayTao value to be equals to 2000-12-31'
    );
    expect(await gopYChatLuongUpdatePage.getTenQuanLyXuongInput()).to.eq(
      'tenQuanLyXuong',
      'Expected TenQuanLyXuong value to be equals to tenQuanLyXuong'
    );
    expect(await gopYChatLuongUpdatePage.getTenKiThuatInput()).to.eq('tenKiThuat', 'Expected TenKiThuat value to be equals to tenKiThuat');
    expect(await gopYChatLuongUpdatePage.getTenQanLyCatInput()).to.eq(
      'tenQanLyCat',
      'Expected TenQanLyCat value to be equals to tenQanLyCat'
    );
    expect(await gopYChatLuongUpdatePage.getTenQAInput()).to.eq('tenQA', 'Expected TenQA value to be equals to tenQA');
    await gopYChatLuongUpdatePage.save();
    expect(await gopYChatLuongUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await gopYChatLuongComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GopYChatLuong', async () => {
    const nbButtonsBeforeDelete = await gopYChatLuongComponentsPage.countDeleteButtons();
    await gopYChatLuongComponentsPage.clickOnLastDeleteButton();

    gopYChatLuongDeleteDialog = new GopYChatLuongDeleteDialog();
    expect(await gopYChatLuongDeleteDialog.getDialogTitle()).to.eq('ctApp.gopYChatLuong.delete.question');
    await gopYChatLuongDeleteDialog.clickOnConfirmButton();

    expect(await gopYChatLuongComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
