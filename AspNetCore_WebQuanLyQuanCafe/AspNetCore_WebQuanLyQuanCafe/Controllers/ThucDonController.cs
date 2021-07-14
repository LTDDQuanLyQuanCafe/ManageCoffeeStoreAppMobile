using AspNetCore_WebQuanLyQuanCafe.Services.Contracts;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace AspNetCore_WebQuanLyQuanCafe.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ThucDonController : ControllerBase
    {
        private readonly IThucDonServices _thucDonServices;

        public ThucDonController(IThucDonServices thucDonServices)
        {
            _thucDonServices = thucDonServices;
        }

        // GET: api/all
        [HttpGet]
        [Route("all")]
        public IActionResult GetAllThucDon()
        {
            var listThucDon = _thucDonServices.GetAllThucDon();
            return Ok(listThucDon.Result);
        }

    }
}
