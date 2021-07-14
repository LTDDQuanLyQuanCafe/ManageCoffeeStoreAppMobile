using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Models.ConnectSQL
{
    public class SqlConnectDB
    {
        public SqlConnection sqlConnection { get; }
        public SqlConnectDB(string connectionString)
        {
            sqlConnection = new SqlConnection(connectionString);
        }
        public async Task<bool> OpenAsync()
        {
            try
            {
                if (sqlConnection.State == System.Data.ConnectionState.Closed)
                {
                    await sqlConnection.OpenAsync();
                }
                return true;
            }
            catch
            {
                return false;
            }
        }
        public async Task<bool> CloseAsync()
        {
            try
            {
                if (sqlConnection.State == System.Data.ConnectionState.Open)
                {
                    await sqlConnection.CloseAsync();
                }
                return true;
            }
            catch
            {
                return false;
            }
        }
    }
}
