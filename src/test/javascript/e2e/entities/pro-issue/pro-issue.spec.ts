import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProIssueComponentsPage, ProIssueDeleteDialog, ProIssueUpdatePage } from './pro-issue.page-object';

const expect = chai.expect;

describe('ProIssue e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proIssueComponentsPage: ProIssueComponentsPage;
  let proIssueUpdatePage: ProIssueUpdatePage;
  let proIssueDeleteDialog: ProIssueDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProIssues', async () => {
    await navBarPage.goToEntity('pro-issue');
    proIssueComponentsPage = new ProIssueComponentsPage();
    await browser.wait(ec.visibilityOf(proIssueComponentsPage.title), 5000);
    expect(await proIssueComponentsPage.getTitle()).to.eq('ctApp.proIssue.home.title');
  });

  it('should load create ProIssue page', async () => {
    await proIssueComponentsPage.clickOnCreateButton();
    proIssueUpdatePage = new ProIssueUpdatePage();
    expect(await proIssueUpdatePage.getPageTitle()).to.eq('ctApp.proIssue.home.createOrEditLabel');
    await proIssueUpdatePage.cancel();
  });

  it('should create and save ProIssues', async () => {
    const nbButtonsBeforeCreate = await proIssueComponentsPage.countDeleteButtons();

    await proIssueComponentsPage.clickOnCreateButton();
    await promise.all([
      proIssueUpdatePage.setMaHangInput('maHang'),
      proIssueUpdatePage.setPonoInput('pono'),
      proIssueUpdatePage.setTenChuyenInput('tenChuyen'),
      proIssueUpdatePage.setNgayGioBatDauInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proIssueUpdatePage.setNgayGioKetThucInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proIssueUpdatePage.setMaLoiInput('maLoi'),
      proIssueUpdatePage.setMoTaLoiInput('moTaLoi'),
      proIssueUpdatePage.setMaMayInput('maMay'),
      proIssueUpdatePage.setSoTramInput('soTram'),
      proIssueUpdatePage.setProChuyenIdInput('5'),
      proIssueUpdatePage.setProSanPhamIdInput('5'),
      proIssueUpdatePage.setNguoiTaoInput('nguoiTao'),
      proIssueUpdatePage.setCheckPointTypeInput('checkPointType'),
      proIssueUpdatePage.setCheckPointIdInput('5'),
      proIssueUpdatePage.setCheckPointNameInput('checkPointName')
    ]);
    expect(await proIssueUpdatePage.getMaHangInput()).to.eq('maHang', 'Expected MaHang value to be equals to maHang');
    expect(await proIssueUpdatePage.getPonoInput()).to.eq('pono', 'Expected Pono value to be equals to pono');
    expect(await proIssueUpdatePage.getTenChuyenInput()).to.eq('tenChuyen', 'Expected TenChuyen value to be equals to tenChuyen');
    expect(await proIssueUpdatePage.getNgayGioBatDauInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayGioBatDau value to be equals to 2000-12-31'
    );
    expect(await proIssueUpdatePage.getNgayGioKetThucInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayGioKetThuc value to be equals to 2000-12-31'
    );
    expect(await proIssueUpdatePage.getMaLoiInput()).to.eq('maLoi', 'Expected MaLoi value to be equals to maLoi');
    expect(await proIssueUpdatePage.getMoTaLoiInput()).to.eq('moTaLoi', 'Expected MoTaLoi value to be equals to moTaLoi');
    expect(await proIssueUpdatePage.getMaMayInput()).to.eq('maMay', 'Expected MaMay value to be equals to maMay');
    expect(await proIssueUpdatePage.getSoTramInput()).to.eq('soTram', 'Expected SoTram value to be equals to soTram');
    expect(await proIssueUpdatePage.getProChuyenIdInput()).to.eq('5', 'Expected proChuyenId value to be equals to 5');
    expect(await proIssueUpdatePage.getProSanPhamIdInput()).to.eq('5', 'Expected proSanPhamId value to be equals to 5');
    expect(await proIssueUpdatePage.getNguoiTaoInput()).to.eq('nguoiTao', 'Expected NguoiTao value to be equals to nguoiTao');
    expect(await proIssueUpdatePage.getCheckPointTypeInput()).to.eq(
      'checkPointType',
      'Expected CheckPointType value to be equals to checkPointType'
    );
    expect(await proIssueUpdatePage.getCheckPointIdInput()).to.eq('5', 'Expected checkPointId value to be equals to 5');
    expect(await proIssueUpdatePage.getCheckPointNameInput()).to.eq(
      'checkPointName',
      'Expected CheckPointName value to be equals to checkPointName'
    );
    await proIssueUpdatePage.save();
    expect(await proIssueUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proIssueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProIssue', async () => {
    const nbButtonsBeforeDelete = await proIssueComponentsPage.countDeleteButtons();
    await proIssueComponentsPage.clickOnLastDeleteButton();

    proIssueDeleteDialog = new ProIssueDeleteDialog();
    expect(await proIssueDeleteDialog.getDialogTitle()).to.eq('ctApp.proIssue.delete.question');
    await proIssueDeleteDialog.clickOnConfirmButton();

    expect(await proIssueComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
