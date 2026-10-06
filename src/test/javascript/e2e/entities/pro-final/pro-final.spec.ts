import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ProFinalComponentsPage, ProFinalDeleteDialog, ProFinalUpdatePage } from './pro-final.page-object';

const expect = chai.expect;

describe('ProFinal e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let proFinalComponentsPage: ProFinalComponentsPage;
  let proFinalUpdatePage: ProFinalUpdatePage;
  let proFinalDeleteDialog: ProFinalDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ProFinals', async () => {
    await navBarPage.goToEntity('pro-final');
    proFinalComponentsPage = new ProFinalComponentsPage();
    await browser.wait(ec.visibilityOf(proFinalComponentsPage.title), 5000);
    expect(await proFinalComponentsPage.getTitle()).to.eq('ctApp.proFinal.home.title');
  });

  it('should load create ProFinal page', async () => {
    await proFinalComponentsPage.clickOnCreateButton();
    proFinalUpdatePage = new ProFinalUpdatePage();
    expect(await proFinalUpdatePage.getPageTitle()).to.eq('ctApp.proFinal.home.createOrEditLabel');
    await proFinalUpdatePage.cancel();
  });

  it('should create and save ProFinals', async () => {
    const nbButtonsBeforeCreate = await proFinalComponentsPage.countDeleteButtons();

    await proFinalComponentsPage.clickOnCreateButton();
    await promise.all([
      proFinalUpdatePage.setKhxhIdInput('5'),
      proFinalUpdatePage.setMaHangInput('maHang'),
      proFinalUpdatePage.setPoNoInput('poNo'),
      proFinalUpdatePage.setNgayKiemFinalInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proFinalUpdatePage.setTenChuyenInput('tenChuyen'),
      proFinalUpdatePage.setNgayGioKiemInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      proFinalUpdatePage.setSoLuongKiemAqlInput('5'),
      proFinalUpdatePage.setSoLuongInput('5'),
      proFinalUpdatePage.setDoAmInput('5'),
      proFinalUpdatePage.setTongSoLoiInput('5'),
      proFinalUpdatePage.setSoThuTuThungInput('5'),
      proFinalUpdatePage.setDoKimMdInput('doKimMd'),
      proFinalUpdatePage.setAqlInput('aql'),
      proFinalUpdatePage.setMaxLoiChoPhepInput('5'),
      proFinalUpdatePage.setSoLuongPoKiemInput('5'),
      proFinalUpdatePage.setNguoiKiemInput('nguoiKiem')
    ]);
    expect(await proFinalUpdatePage.getKhxhIdInput()).to.eq('5', 'Expected khxhId value to be equals to 5');
    expect(await proFinalUpdatePage.getMaHangInput()).to.eq('maHang', 'Expected MaHang value to be equals to maHang');
    expect(await proFinalUpdatePage.getPoNoInput()).to.eq('poNo', 'Expected PoNo value to be equals to poNo');
    expect(await proFinalUpdatePage.getNgayKiemFinalInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayKiemFinal value to be equals to 2000-12-31'
    );
    expect(await proFinalUpdatePage.getTenChuyenInput()).to.eq('tenChuyen', 'Expected TenChuyen value to be equals to tenChuyen');
    expect(await proFinalUpdatePage.getNgayGioKiemInput()).to.contain(
      '2001-01-01T02:30',
      'Expected ngayGioKiem value to be equals to 2000-12-31'
    );
    expect(await proFinalUpdatePage.getSoLuongKiemAqlInput()).to.eq('5', 'Expected soLuongKiemAql value to be equals to 5');
    expect(await proFinalUpdatePage.getSoLuongInput()).to.eq('5', 'Expected soLuong value to be equals to 5');
    expect(await proFinalUpdatePage.getDoAmInput()).to.eq('5', 'Expected doAm value to be equals to 5');
    expect(await proFinalUpdatePage.getTongSoLoiInput()).to.eq('5', 'Expected tongSoLoi value to be equals to 5');
    expect(await proFinalUpdatePage.getSoThuTuThungInput()).to.eq('5', 'Expected soThuTuThung value to be equals to 5');
    expect(await proFinalUpdatePage.getDoKimMdInput()).to.eq('doKimMd', 'Expected DoKimMd value to be equals to doKimMd');
    expect(await proFinalUpdatePage.getAqlInput()).to.eq('aql', 'Expected Aql value to be equals to aql');
    expect(await proFinalUpdatePage.getMaxLoiChoPhepInput()).to.eq('5', 'Expected maxLoiChoPhep value to be equals to 5');
    const selectedCompleted = proFinalUpdatePage.getCompletedInput();
    if (await selectedCompleted.isSelected()) {
      await proFinalUpdatePage.getCompletedInput().click();
      expect(await proFinalUpdatePage.getCompletedInput().isSelected(), 'Expected completed not to be selected').to.be.false;
    } else {
      await proFinalUpdatePage.getCompletedInput().click();
      expect(await proFinalUpdatePage.getCompletedInput().isSelected(), 'Expected completed to be selected').to.be.true;
    }
    expect(await proFinalUpdatePage.getSoLuongPoKiemInput()).to.eq('5', 'Expected soLuongPoKiem value to be equals to 5');
    const selectedPass = proFinalUpdatePage.getPassInput();
    if (await selectedPass.isSelected()) {
      await proFinalUpdatePage.getPassInput().click();
      expect(await proFinalUpdatePage.getPassInput().isSelected(), 'Expected pass not to be selected').to.be.false;
    } else {
      await proFinalUpdatePage.getPassInput().click();
      expect(await proFinalUpdatePage.getPassInput().isSelected(), 'Expected pass to be selected').to.be.true;
    }
    expect(await proFinalUpdatePage.getNguoiKiemInput()).to.eq('nguoiKiem', 'Expected NguoiKiem value to be equals to nguoiKiem');
    await proFinalUpdatePage.save();
    expect(await proFinalUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await proFinalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ProFinal', async () => {
    const nbButtonsBeforeDelete = await proFinalComponentsPage.countDeleteButtons();
    await proFinalComponentsPage.clickOnLastDeleteButton();

    proFinalDeleteDialog = new ProFinalDeleteDialog();
    expect(await proFinalDeleteDialog.getDialogTitle()).to.eq('ctApp.proFinal.delete.question');
    await proFinalDeleteDialog.clickOnConfirmButton();

    expect(await proFinalComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
