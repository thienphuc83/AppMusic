package com.example.tpmusic.Service;

import com.example.tpmusic.Model.Album;
import com.example.tpmusic.Model.BaiHat;
import com.example.tpmusic.Model.ChuDe;
import com.example.tpmusic.Model.PlayList;
import com.example.tpmusic.Model.QuangCao;
import com.example.tpmusic.Model.TheLoai;
import com.example.tpmusic.Model.TheLoaiTrongNgay;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

    @GET("playlistforcurrentday.php")
    Call<List<PlayList>> GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<TheLoaiTrongNgay> GetChuDeTheLoaiTrongNgay();

    @GET("album.php")
    Call<List<Album>> GetAlbumHot();

    @GET("baihatyeuthich.php")
    Call<List<BaiHat>> GetBaiHatHot();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoPlayList(@Field("idplaylist") String idplaylist);

    @GET("danhsachplaylist.php")
    Call<List<PlayList>> GetDanhSachPlayList();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);

    @GET("allchude.php")
    Call<List<ChuDe>> GetDanhSachChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetDanhSachTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("allalbum.php")
    Call<List<Album>> GetDanhSachAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<BaiHat>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThichBaiHat(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<BaiHat>> ReSearchBaiHat(@Field("tukhoa") String tukhoa);


}
