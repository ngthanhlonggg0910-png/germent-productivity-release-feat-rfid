import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NVButtonComponentsPage, NVButtonDeleteDialog, NVButtonUpdatePage } from './nv-button.page-object';

const expect = chai.expect;

describe('NVButton e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let nVButtonComponentsPage: NVButtonComponentsPage;
  let nVButtonUpdatePage: NVButtonUpdatePage;
  let nVButtonDeleteDialog: NVButtonDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load NVButtons', async () => {
    await navBarPage.goToEntity('nv-button');
    nVButtonComponentsPage = new NVButtonComponentsPage();
    await browser.wait(ec.visibilityOf(nVButtonComponentsPage.title), 5000);
    expect(await nVButtonComponentsPage.getTitle()).to.eq('ctApp.nVButton.home.title');
  });

  it('should load create NVButton page', async () => {
    await nVButtonComponentsPage.clickOnCreateButton();
    nVButtonUpdatePage = new NVButtonUpdatePage();
    expect(await nVButtonUpdatePage.getPageTitle()).to.eq('ctApp.nVButton.home.createOrEditLabel');
    await nVButtonUpdatePage.cancel();
  });

  it('should create and save NVButtons', async () => {
    const nbButtonsBeforeCreate = await nVButtonComponentsPage.countDeleteButtons();

    await nVButtonComponentsPage.clickOnCreateButton();
    await promise.all([
      nVButtonUpdatePage.setNameInput('name'),
      nVButtonUpdatePage.setDiscoveredOnTopicInput('discoveredOnTopic'),
      nVButtonUpdatePage.setCmndTopicInput('cmndTopic'),
      nVButtonUpdatePage.setPlOffInput('plOff'),
      nVButtonUpdatePage.setPlOnInput('plOn'),
      nVButtonUpdatePage.setUniqIdInput('uniqId'),
      nVButtonUpdatePage.setDevIdsInput('devIds'),
      nVButtonUpdatePage.setTeleTopicInput('teleTopic'),
      nVButtonUpdatePage.setStatTopicInput('statTopic'),
      nVButtonUpdatePage.setAvtyTopicInput('avtyTopic'),
      nVButtonUpdatePage.setPlAvailInput('plAvail'),
      nVButtonUpdatePage.setPlNotAvailInput('plNotAvail'),
      nVButtonUpdatePage.stateSelectLastOption(),
      nVButtonUpdatePage.lwtSelectLastOption(),
      nVButtonUpdatePage.setLastUpdateStateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      nVButtonUpdatePage.groupTypeSelectLastOption(),
      nVButtonUpdatePage.buttonTrackingSelectLastOption(),
      nVButtonUpdatePage.deviceSelectLastOption(),
      nVButtonUpdatePage.trackingTypeSelectLastOption()
    ]);
    expect(await nVButtonUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await nVButtonUpdatePage.getDiscoveredOnTopicInput()).to.eq(
      'discoveredOnTopic',
      'Expected DiscoveredOnTopic value to be equals to discoveredOnTopic'
    );
    expect(await nVButtonUpdatePage.getCmndTopicInput()).to.eq('cmndTopic', 'Expected CmndTopic value to be equals to cmndTopic');
    expect(await nVButtonUpdatePage.getPlOffInput()).to.eq('plOff', 'Expected PlOff value to be equals to plOff');
    expect(await nVButtonUpdatePage.getPlOnInput()).to.eq('plOn', 'Expected PlOn value to be equals to plOn');
    expect(await nVButtonUpdatePage.getUniqIdInput()).to.eq('uniqId', 'Expected UniqId value to be equals to uniqId');
    expect(await nVButtonUpdatePage.getDevIdsInput()).to.eq('devIds', 'Expected DevIds value to be equals to devIds');
    expect(await nVButtonUpdatePage.getTeleTopicInput()).to.eq('teleTopic', 'Expected TeleTopic value to be equals to teleTopic');
    expect(await nVButtonUpdatePage.getStatTopicInput()).to.eq('statTopic', 'Expected StatTopic value to be equals to statTopic');
    expect(await nVButtonUpdatePage.getAvtyTopicInput()).to.eq('avtyTopic', 'Expected AvtyTopic value to be equals to avtyTopic');
    expect(await nVButtonUpdatePage.getPlAvailInput()).to.eq('plAvail', 'Expected PlAvail value to be equals to plAvail');
    expect(await nVButtonUpdatePage.getPlNotAvailInput()).to.eq('plNotAvail', 'Expected PlNotAvail value to be equals to plNotAvail');
    expect(await nVButtonUpdatePage.getLastUpdateStateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastUpdateState value to be equals to 2000-12-31'
    );
    await nVButtonUpdatePage.save();
    expect(await nVButtonUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await nVButtonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last NVButton', async () => {
    const nbButtonsBeforeDelete = await nVButtonComponentsPage.countDeleteButtons();
    await nVButtonComponentsPage.clickOnLastDeleteButton();

    nVButtonDeleteDialog = new NVButtonDeleteDialog();
    expect(await nVButtonDeleteDialog.getDialogTitle()).to.eq('ctApp.nVButton.delete.question');
    await nVButtonDeleteDialog.clickOnConfirmButton();

    expect(await nVButtonComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
