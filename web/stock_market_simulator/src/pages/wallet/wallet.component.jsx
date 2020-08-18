import React from 'react';
import WalletForm from '../../components/wallet-form/wallet-form.component';
import WalletTable from '../../components/wallet-table/wallet-table.component';

const WalletPage = () => (
  <div style={{display:"flex", width:"100%", justifyContent:"space-between"}}>
    <div style={{width:"40%", marginLeft:"5%"}}>
    <WalletForm />
    </div>
    <div style={{width:"40%", marginRight:"5%"}}>
    <WalletTable />
    </div>
  </div>
);

export default WalletPage;
