window.initMap = function () {
    // 지도 중심과 줌 레벨을 설정합니다.
    const mapCenter = { lat: 37.54242819397572, lng: 126.72854176533158 };
    const zoomLevel = 17; // 원하는 줌 레벨로 설정하세요.

    const map = new google.maps.Map(document.getElementById("map"), {
        center: mapCenter,
        zoom: zoomLevel,
        gestureHandling: "auto"
    });

    const malls = [
        { label: "Church", name: "하손들교회", lat: 37.54141401015502, lng: 126.72927810037858 },
    ];

    const infoWindow = new google.maps.InfoWindow();

    malls.forEach(({ label, name, lat, lng }) => {
        const marker = new google.maps.Marker({
            position: { lat, lng },
            label,
            map
        });
        bounds.extend(marker.position);

        marker.addListener("click", () => {
            map.panTo(marker.position);
            infoWindow.setContent(name);
            infoWindow.open({
                anchor: marker,
                map
            });
        });
    });

};
