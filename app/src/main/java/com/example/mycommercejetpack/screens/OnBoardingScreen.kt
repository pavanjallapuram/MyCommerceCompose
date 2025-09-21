package com.example.mycommercejetpack.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycommercejetpack.R
import com.example.mycommercejetpack.data.OnBoardingPageItem
import com.example.mycommercejetpack.ui.theme.BgGrey
import com.example.mycommercejetpack.ui.theme.BlueCustom
import com.example.mycommercejetpack.ui.theme.DotColor

@Preview
@Composable
fun OnBoardingPage(onBoardingEndded: () -> Unit = {}){

    val onBoardingList = mutableListOf<OnBoardingPageItem>()
    onBoardingList.add(OnBoardingPageItem(R.drawable.ic_sample_onboard,"Hello","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus."))
    onBoardingList.add(OnBoardingPageItem(R.drawable.ic_sample_onboard,"Hello","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus."))
    onBoardingList.add(OnBoardingPageItem(R.drawable.ic_sample_onboard,"Hello","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus."))
    onBoardingList.add(OnBoardingPageItem(R.drawable.ic_sample_onboard,"Hello","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non consectetur turpis. Morbi eu eleifend lacus."))

    val pageCount1  = onBoardingList.size

    val pagerState = rememberPagerState(initialPage = 0, initialPageOffsetFraction = 0.0f, pageCount = {pageCount1})

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.ic_create_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Box(modifier = Modifier.fillMaxSize(0.85f)) {

            HorizontalPager(
                state = pagerState,
                pageSpacing = 16.dp,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.90f)
            ) { page ->

                if (page+1 >= pageCount1){
                    OnBoardingPagerItem(onBoardingList[page],true,onBoardingEndded)
                }
                else{
                    OnBoardingPagerItem(onBoardingList[page],false,onBoardingEndded)
                }



            }



            Box(modifier = Modifier.padding(20.dp).align(alignment = Alignment.BottomCenter)) {

                DotsIndicator(
                    totalDots = pageCount1,
                    selectedIndex = pagerState.currentPage,
                    selectedColor = BlueCustom,
                    unSelectedColor = DotColor,
                    dotSize = 15.dp,
                    dotSpacing = 8.dp
                )

            }

        }






    }





}



@Composable
fun OnBoardingPagerItem(onBoardingPageItem: OnBoardingPageItem,getStartedVisible: Boolean,onBoardingEndded: () -> Unit = {}){

    val ralewayBold = FontFamily(Font(R.font.raleway_bold, FontWeight.Bold))
    val nunitosans = FontFamily(Font(R.font.nunitosanslight, FontWeight.Light))

    Card(modifier = Modifier.fillMaxSize(), shape = RoundedCornerShape(25.dp)){

        Column {
            Image(painterResource(onBoardingPageItem.image),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth(1.0f).fillMaxHeight(0.5f), contentScale = ContentScale.FillBounds)


            Box(modifier = Modifier.fillMaxSize(1.0f).background(Color.White), contentAlignment = Alignment.Center) {

                Column(modifier = Modifier.padding(30.dp,0.dp)) {

                    Text(onBoardingPageItem.title, modifier = Modifier.fillMaxWidth(), fontSize = 45.sp, fontFamily = ralewayBold, textAlign = TextAlign.Center)

                    Text(onBoardingPageItem.descrpt, fontSize = 20.sp,
                        fontFamily = nunitosans, textAlign = TextAlign.Center)

                    if (getStartedVisible) {
                        Button(onClick = {
                            onBoardingEndded()
                        },
                            modifier = Modifier.fillMaxWidth(0.65f).height(40.dp).align(Alignment.CenterHorizontally).padding(0.dp,3.dp).padding(0.dp,0.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = BlueCustom, // Background color
                                contentColor = Color.White   // Text/Icon color
                            ) ) {
                            Text("Let's get started", fontSize = 12.sp,
                                fontFamily = nunitosans,
                                color = Color.White, textAlign = TextAlign.Center)

                        }
                    }


                }




            }
        }


    }

}



@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color ,
    unSelectedColor: Color,
    dotSize: Dp = 10.dp,
    dotSpacing: Dp = 6.dp
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.wrapContentWidth()
    ) {
        repeat(totalDots) { index ->
            Box(
                modifier = Modifier
                    .padding(horizontal = dotSpacing / 2)
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(if (index == selectedIndex) selectedColor else unSelectedColor)
            )
        }
    }
}